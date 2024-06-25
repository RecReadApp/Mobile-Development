package id.recread.repository

import id.recread.network.NetworkService
import retrofit2.Call

class LoginRepository(private val networkService: NetworkService) {

    suspend fun doLogin(username: String, password: String): Call<Boolean> {
        return networkService.login(username, password)
    }

}