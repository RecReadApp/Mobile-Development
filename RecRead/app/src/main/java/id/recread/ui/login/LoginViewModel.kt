package id.recread.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.recread.repository.LoginRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _state = MutableLiveData<Boolean?>()
    val state: LiveData<Boolean?> = _state

    fun doLogin(username: String, password: String) {
        _state.value = true

        /* TODO: Uncomment below code if you want to get data from API */
        /*viewModelScope.launch {
            loginRepository.doLogin(username, password).enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    if (response.isSuccessful) {
                        _state.value = true
                    } else {
                        Log.e("LoginViewModel", "response failed")
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.e("LoginViewModel", t.localizedMessage ?: "")
                }
            })
        }*/
    }

    fun successLogin() {
        _state.value = null
    }
}