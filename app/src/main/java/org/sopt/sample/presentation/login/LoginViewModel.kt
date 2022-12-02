package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.dto.request.RequestLoginDto
import org.sopt.sample.data.dto.response.ResponseLoginDto
import org.sopt.sample.data.local.State
import org.sopt.sample.data.repository.AuthRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    // Backing Property
    private val _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: LiveData<ResponseLoginDto>
        get() = _loginResult

    private val _stateMessage = MutableLiveData<State>()
    val stateMessage: LiveData<State>
        get() = _stateMessage

    val emailText = MutableLiveData("")
    val pwdText = MutableLiveData("")

    /** 서버에 로그인 요청 */
    fun login(email: String, password: String) {
        if (!checkEmail(email)) {
            _stateMessage.value = State.INCORRECT_EMAIL
            return
        }

        if (!checkPwd(password)) {
            _stateMessage.value = State.INCORRECT_PWD
            return
        }

        viewModelScope.launch {
            authRepository.login(RequestLoginDto(email, password))
                .onSuccess { response ->
                    if (response.status in 200..300) {
                        Timber.d("LOGIN SUCCESS")
                        Timber.d("response : $response")
                        _loginResult.value = response
                        _stateMessage.value = State.SUCCESS
                    } else {
                        Timber.e("LOGIN FAIL")
                        Timber.e("status code : $response.status")
                        Timber.e("message : $response.message")
                        _stateMessage.value = State.FAIL
                    }
                }
                .onFailure {
                    Timber.e("LOGIN SERVER ERROR")
                    Timber.e("message : " + it.message)
                    _stateMessage.value = State.SERVER_ERROR
                }
        }
    }

    /** 이메일 유효성 검사 */
    private fun checkEmail(email: String): Boolean {
        return email.length in 6..10
    }

    /** 비밀번호 유효성 검사 */
    private fun checkPwd(pwd: String): Boolean {
        return pwd.length in 6..12
    }
}
