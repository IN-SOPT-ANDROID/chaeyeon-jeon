package org.sopt.sample.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.api.ServicePool
import org.sopt.sample.data.entity.response.ResponseGetFollowerListDto
import org.sopt.sample.data.entity.response.ResponseGetFollowerListDto.Follower
import org.sopt.sample.data.local.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel : ViewModel() {
    private val followerService = ServicePool.followerService

    private val _followerList = MutableLiveData<List<Follower>>()
    val followerList: LiveData<List<Follower>>
        get() = _followerList

    private val _stateMessage = MutableLiveData<State>()
    val stateMessage: LiveData<State>
        get() = _stateMessage

    /** Reqres 서버에 팔로워 리스트 중 1페이지 요청 */
    fun getFollowerList() {
        followerService.getFollowerList(1).enqueue(object : Callback<ResponseGetFollowerListDto> {
            override fun onResponse(
                call: Call<ResponseGetFollowerListDto>,
                response: Response<ResponseGetFollowerListDto>
            ) {
                if (response.isSuccessful()) {
                    // 팔로워가 존재하지 않는 경우
                    if (response.body()?.data == null) {
                        Timber.d("GET FOLLOWER LIST IS NULL")
                        _stateMessage.value = State.NULL
                        return
                    }

                    Timber.d("GET FOLLOWER LIST SUCCESS")
                    Timber.d("response : " + response.body())
                    val tempFollowerList = mutableListOf<Follower>()
                    for (follower in response.body()?.data!!) {
                        tempFollowerList.add(follower)
                    }
                    _followerList.value = tempFollowerList
                    _stateMessage.value = State.SUCCESS
                } else {
                    Timber.e("GET FOLLOWER LIST FAIL")
                    Timber.e("code : " + response.code())
                    Timber.e("message : " + response.message())
                    _stateMessage.value = State.FAIL
                }
            }

            override fun onFailure(call: Call<ResponseGetFollowerListDto>, t: Throwable) {
                _stateMessage.value = State.SERVER_ERROR
                Timber.e("GET FOLLOWER LIST SERVER ERROR")
                Timber.e("message : " + t.message)
            }
        })
    }
}
