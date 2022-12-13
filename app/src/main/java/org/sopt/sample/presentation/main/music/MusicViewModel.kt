package org.sopt.sample.presentation.main.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.dto.response.ResponseMusicDto
import org.sopt.sample.data.local.UiState
import org.sopt.sample.data.repository.MusicRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicRepository: MusicRepository
) : ViewModel() {
    private val _musicList = MutableLiveData<List<ResponseMusicDto>>()
    val musicList: LiveData<List<ResponseMusicDto>>
        get() = _musicList

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    /** 서버에 음악 리스트 요청 */
    fun getMusicList() {
        viewModelScope.launch {
            musicRepository.getMusicList()
                .onSuccess { response ->
                    // 음악이 존재하지 않는 경우
                    if (response.data == null) {
                        Timber.d("GET MUSIC LIST IS NULL")
                        _stateMessage.value = UiState.NULL
                        return@onSuccess
                    }

                    Timber.d("GET MUSIC LIST SUCCESS")
                    Timber.d("response : $response")
                    _musicList.value = response.data!!
                    _stateMessage.value = UiState.SUCCESS
                }
                .onFailure {
                    Timber.e("GET MUSIC LIST SERVER ERROR")
                    Timber.e("message : ${it.message}")
                    _stateMessage.value = UiState.SERVER_ERROR
                }
        }
    }
}
