package org.sopt.sample.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.dto.response.ResponseGetFollowerListDto.Follower
import org.sopt.sample.util.UiState
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

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    /** Reqres 서버에 팔로워 리스트 중 1페이지 요청 */
    fun getFollowerList() {
        viewModelScope.launch {
            followerRepository.getFollowerList(1)
                .onSuccess { response ->
                    // 팔로워가 존재하지 않는 경우
                    if (response.data == null) {
                        Timber.d("GET FOLLOWER LIST IS NULL")
                        _stateMessage.value = UiState.Failure(FOLLOWER_NULL_CODE)
                        return@onSuccess
                    }

                    Timber.d("GET FOLLOWER LIST SUCCESS")
                    Timber.d("response : $response")
                    _followerList.value = response.data
                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    Timber.e("GET FOLLOWER LIST SERVER ERROR")
                    Timber.e("message : ${it.message}")
                    _stateMessage.value = UiState.Error
                }
        }
    }

    companion object {
        const val FOLLOWER_NULL_CODE = 100
    }
}
