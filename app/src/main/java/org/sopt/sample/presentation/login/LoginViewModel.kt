package org.sopt.sample.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.RequestLoginDto
import org.sopt.sample.data.remote.ResponseLoginDto
import org.sopt.sample.data.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    // Backing Property
    private val _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: LiveData<ResponseLoginDto>
        get() = _loginResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val authService = ServicePool.authService

    fun login(email: String, password: String) {
        authService.login(RequestLoginDto(email, password))
            .enqueue(object : Callback<ResponseLoginDto> {
                override fun onResponse(
                    call: Call<ResponseLoginDto>,
                    response: Response<ResponseLoginDto>
                ) {
                    if (response.isSuccessful) {
                        _loginResult.value = response.body()
                    } else {
                        _errorMessage.value = "로그인에 실패하였습니다."
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                    _errorMessage.value = "오류가 발생하였습니다."
                    Log.e("LOGIN_FAIL", "cause : " + t.cause)
                    Log.e("LOGIN_FAIL", "message : " + t.message)
                }
            })
    }
}
