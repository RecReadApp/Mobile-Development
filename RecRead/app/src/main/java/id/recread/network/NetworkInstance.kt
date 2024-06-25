package id.recread.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkInstance {

    private const val BASE_URL: String = "http://localhost:5000/"
    private const val BASE_URL_FOR_EMU: String = "http://10.0.2.2:5000/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}