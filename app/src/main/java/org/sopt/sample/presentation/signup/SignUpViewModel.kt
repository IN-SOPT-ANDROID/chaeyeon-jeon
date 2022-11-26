package org.sopt.sample.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.RequestSignupDto
import org.sopt.sample.data.remote.ResponseSignupDto
import org.sopt.sample.data.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signupResult = MutableLiveData<ResponseSignupDto>()
    val signupResult: LiveData<ResponseSignupDto>
        get() = _signupResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val authService = ServicePool.authService

    fun signup(email: String, password: String, name: String) {
        authService.signup(RequestSignupDto(email, password, name)).enqueue(object :
                Callback<ResponseSignupDto> {
                override fun onResponse(
                    call: Call<ResponseSignupDto>,
                    response: Response<ResponseSignupDto>
                ) {
                    if (response.isSuccessful) {
                        _signupResult.value = response.body()
                        Log.d("SIGNUP_SUCCESS", "response : " + response.body().toString())
                    } else {
                        _errorMessage.value = "회원가입에 실패했습니다."
                        Log.e("SIGNUP_FAIL", "code : " + response.code())
                        Log.e("SIGNUP_FAIL", "message : " + response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                    _errorMessage.value = "오류가 발생하였습니다."
                    Log.e("SIGNUP_FAIL", "message : " + t.message)
                }
            })
    }
}
