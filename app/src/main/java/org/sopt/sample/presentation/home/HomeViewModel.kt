package org.sopt.sample.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.dto.response.ResponseGetFollowerListDto.Follower
import org.sopt.sample.data.local.State
import org.sopt.sample.data.repository.FollowerRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val followerRepository: FollowerRepository
) : ViewModel() {
    private val _followerList = MutableLiveData<List<Follower>>()
    val followerList: LiveData<List<Follower>>
        get() = _followerList

    private val _stateMessage = MutableLiveData<State>()
    val stateMessage: LiveData<State>
        get() = _stateMessage

    /** Reqres 서버에 팔로워 리스트 중 1페이지 요청 */
    fun getFollowerList() {
        viewModelScope.launch {
            followerRepository.getFollowerList(1)
                .onSuccess { response ->
                    if (response.isSuccessful) {
                        // 팔로워가 존재하지 않는 경우
                        if (response.body()?.data == null) {
                            Timber.d("GET FOLLOWER LIST IS NULL")
                            _stateMessage.value = State.NULL
                            return@onSuccess
                        }

                        Timber.d("GET FOLLOWER LIST SUCCESS")
                        Timber.d("response : $response")
                        val tempFollowerList = mutableListOf<Follower>()
                        for (follower in response.body()?.data!!) {
                            tempFollowerList.add(follower)
                        }
                        _followerList.value = tempFollowerList
                        _stateMessage.value = State.SUCCESS
                    } else {
                        Timber.e("GET FOLLOWER LIST FAIL")
                        Timber.e("code : $response.code()")
                        Timber.e("message : $response.message()")
                        _stateMessage.value = State.FAIL
                    }
                }
                .onFailure {
                    _stateMessage.value = State.SERVER_ERROR
                    Timber.e("GET FOLLOWER LIST SERVER ERROR")
                    Timber.e("message : $it.message")
                }
        }
    }
}
