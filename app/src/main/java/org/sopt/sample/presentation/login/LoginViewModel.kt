package org.sopt.sample.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.entity.ServicePool
import org.sopt.sample.data.entity.request.RequestLoginDto
import org.sopt.sample.data.entity.response.ResponseLoginDto
import org.sopt.sample.data.local.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val authService = ServicePool.authService

    // Backing Property
    private val _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: LiveData<ResponseLoginDto>
        get() = _loginResult

    private val _stateMessage = MutableLiveData<State>()
    val stateMessage: LiveData<State>
        get() = _stateMessage

    /** 서버에 로그인 요청 */
    fun login(email: String, password: String) {
        authService.login(RequestLoginDto(email, password))
            .enqueue(object : Callback<ResponseLoginDto> {
                override fun onResponse(
                    call: Call<ResponseLoginDto>,
                    response: Response<ResponseLoginDto>
                ) {
                    if (response.isSuccessful) {
                        Timber.d("LOGIN SUCCESS")
                        Timber.d("response : " + response.body())
                        _loginResult.value = response.body()
                        _stateMessage.value = State.SUCCESS
                    } else {
                        Timber.e("LOGIN FAIL")
                        Timber.e("code : " + response.code())
                        Timber.e("message : " + response.message())
                        _stateMessage.value = State.FAIL
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                    Timber.e("LOGIN SERVER ERROR")
                    Timber.e("message : " + t.message)
                    _stateMessage.value = State.SERVER_ERROR
                }
            })
    }
}
