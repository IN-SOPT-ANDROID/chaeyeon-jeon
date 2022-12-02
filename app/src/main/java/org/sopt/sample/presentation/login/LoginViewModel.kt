package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.dto.request.RequestLoginDto
import org.sopt.sample.data.local.State
import org.sopt.sample.data.repository.AuthRepository
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    // Backing Property
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
                    Timber.d("LOGIN SUCCESS")
                    Timber.d("response : $response")
                    _stateMessage.value = State.SUCCESS
                }
                .onFailure {
                    if (it is HttpException) {
                        when (it.code()) {
                            LOGIN_FAIL_CODE -> {
                                Timber.e("LOGIN FAIL")
                                Timber.e("status code : ${it.code()}")
                                Timber.e("message : ${it.message}")
                                _stateMessage.value = State.FAIL
                            }
                            else -> {
                                Timber.e("LOGIN SERVER ERROR")
                                Timber.e("message : ${it.message}")
                                _stateMessage.value = State.SERVER_ERROR
                            }
                        }
                    }
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

    companion object {
        private const val LOGIN_FAIL_CODE = 400
    }
}
