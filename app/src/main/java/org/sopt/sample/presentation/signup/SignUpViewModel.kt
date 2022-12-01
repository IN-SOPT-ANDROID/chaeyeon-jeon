package org.sopt.sample.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.sopt.sample.api.ServicePool
import org.sopt.sample.data.entity.request.RequestSignupDto
import org.sopt.sample.data.entity.response.ResponseSignupDto
import org.sopt.sample.data.local.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SignUpViewModel : ViewModel() {
    private val authService = ServicePool.authService

    private val _stateMessage = MutableLiveData<State>()
    val stateMessage: LiveData<State>
        get() = _stateMessage

    val emailText = MutableLiveData("")
    val pwdText = MutableLiveData("")
    val nameText = MutableLiveData("")

    val isValidEmail = Transformations.map(emailText) { checkEmail(it) }
    val isValidPwd = Transformations.map(pwdText) { checkPwd(it) }

    /** 이메일 유효성 검사 */
    private fun checkEmail(email: String): Boolean {
        return email.isEmpty() || email.length in 6..10
    }

    /** 비밀번호 유효성 검사 */
    private fun checkPwd(pwd: String): Boolean {
        return pwd.isEmpty() || pwd.length in 6..12
    }

    /** 서버에 회원가입 요청 */
    fun signup(email: String, password: String, name: String) {
        authService.signup(RequestSignupDto(email, password, name)).enqueue(object :
                Callback<ResponseSignupDto> {
                override fun onResponse(
                    call: Call<ResponseSignupDto>,
                    response: Response<ResponseSignupDto>
                ) {
                    if (response.isSuccessful) {
                        Timber.d("SIGNUP SUCCESS")
                        Timber.d("response : " + response.body())
                        _stateMessage.value = State.SUCCESS
                    } else {
                        Timber.e("SIGNUP FAIL")
                        Timber.e("code : " + response.code())
                        Timber.e("message : " + response.message())
                        _stateMessage.value = State.FAIL
                    }
                }

                override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                    Timber.e("SIGNUP SERVER ERROR")
                    Timber.e("message : " + t.message)
                    _stateMessage.value = State.SERVER_ERROR
                }
            })
    }
}
