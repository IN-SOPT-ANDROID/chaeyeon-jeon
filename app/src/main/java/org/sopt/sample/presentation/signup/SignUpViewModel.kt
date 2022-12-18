package org.sopt.sample.presentation.signup

import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.sample.data.dto.request.RequestSignupDto
import org.sopt.sample.util.UiState
import org.sopt.sample.data.repository.AuthRepository
import retrofit2.HttpException
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _stateMessage = MutableLiveData<UiState>()
    val stateMessage: LiveData<UiState>
        get() = _stateMessage

    val emailText = MutableLiveData("")
    val pwdText = MutableLiveData("")
    val nameText = MutableLiveData("")

    val isValidEmail = Transformations.map(emailText) { checkEmail(it) }
    val isValidPwd = Transformations.map(pwdText) { checkPwd(it) }

    /** 이메일 유효성 검사 */
    private fun checkEmail(email: String): Boolean {
        return email.isEmpty() || Pattern.matches(EMAIL_PATTERN, email)
    }

    /** 비밀번호 유효성 검사 */
    private fun checkPwd(pwd: String): Boolean {
        return pwd.isEmpty() || Pattern.matches(PWD_PATTERN, pwd)
    }

    /** 서버에 회원가입 요청 */
    fun signup(email: String, password: String, name: String) {
        viewModelScope.launch {
            authRepository.signup(RequestSignupDto(email, password, name))
                .onSuccess { response ->
                    Timber.d("SIGNUP SUCCESS")
                    Timber.d("response : $response")
                    _stateMessage.value = UiState.Success
                }
                .onFailure {
                    if (it is HttpException) {
                        Timber.e("LOGIN FAIL")
                        Timber.e("code : ${it.code()}")
                        Timber.e("message : ${it.message}")

                        when (it.code()) {
                            SIGNUP_FAIL_CODE ->
                                _stateMessage.value =
                                    UiState.Failure(SIGNUP_FAIL_CODE)
                            else -> _stateMessage.value = UiState.Error
                        }
                    }
                }
        }
    }

    companion object {
        private const val EMAIL_PATTERN = """^(?=.*[a-zA-Z])(?=.*\d).{6,10}$"""
        private const val PWD_PATTERN = """^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()?]).{6,12}$"""

        const val SIGNUP_FAIL_CODE = 400
    }
}
