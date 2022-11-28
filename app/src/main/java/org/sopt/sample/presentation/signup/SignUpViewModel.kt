package org.sopt.sample.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.local.State
import org.sopt.sample.data.entity.ServicePool
import org.sopt.sample.data.entity.request.RequestSignupDto
import org.sopt.sample.data.entity.response.ResponseSignupDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SignUpViewModel : ViewModel() {
    private val authService = ServicePool.authService

    private val _signupResult = MutableLiveData<ResponseSignupDto>()
    val signupResult: LiveData<ResponseSignupDto>
        get() = _signupResult

    private val _stateMessage = MutableLiveData<State>()
    val stateMessage: LiveData<State>
        get() = _stateMessage

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
                        _signupResult.value = response.body()
                        _stateMessage.value = State.SUCCESS
                    } else {
                        _stateMessage.value = State.FAIL
                        Timber.e("SIGNUP FAIL")
                        Timber.e("code : " + response.code())
                        Timber.e("message : " + response.message())
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
