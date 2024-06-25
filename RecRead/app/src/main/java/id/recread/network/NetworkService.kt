package id.recread.network

import id.recread.model.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkService {

    @GET("users")
    suspend fun getUsers(
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("confPassword") confPassword: String,
        @Query("role") role: String
    ): Call<Boolean>

    @POST("users/")
    suspend fun getDetailUser(@Query("id") id: String): Call<Nothing>

    @POST("login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<Boolean>

    @GET("me")
    suspend fun getLoggedInUser(): Call<Nothing>

    @POST("logout")
    suspend fun logout(): Call<Nothing>

    @GET("products")
    suspend fun getProducts(): Call<List<Article>>

}