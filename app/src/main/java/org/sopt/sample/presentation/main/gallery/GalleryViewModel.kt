package org.sopt.sample.presentation.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.dto.request.RequestRegisterMusicDto
import org.sopt.sample.data.local.UiState
import org.sopt.sample.data.repository.MusicRepository
import org.sopt.sample.util.ContentUriRequestBody
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val musicRepository: MusicRepository
) : ViewModel() {
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    val titleText = MutableLiveData("")
    val singerText = MutableLiveData("")

    /** 뷰모델에 사진 저장 */
    fun setCoverImage(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    /** 서버에 음악 등록 요청 */
    fun registerMusic(title: String, singer: String) {
        // 사진이 등록되지 않은 경우
        if (image.value == null) {
            _stateMessage.value = UiState.NULL
            return
        }

        viewModelScope.launch {
            val requestBody = RequestRegisterMusicDto(singer, title).toJsonObject()
            musicRepository.registerMusic(image.value!!.toFormData(), requestBody)
                .onSuccess { response ->
                    Timber.d("REGISTER MUSIC SUCCESS")
                    Timber.d("response: $response")
                    _stateMessage.value = UiState.SUCCESS
                }
                .onFailure {
                    if (it is HttpException) {
                        // 예외 처리는 미래의 내가!
//                        when (it.code()) {
//                         IMAGE_NULL_CODE -> {}
//                         INCORRECT_INPUT_CODE -> {}
//                            else -> {
                        Timber.e("REGISTER MUSIC SERVER ERROR")
                        Timber.e("message : ${it.message}")
                        _stateMessage.value = UiState.SERVER_ERROR
//                            }
//                        }
                    }
                }
        }
    }

    companion object {
        private const val IMAGE_NULL_CODE = 404
        private const val INCORRECT_INPUT_CODE = 400
    }
}
