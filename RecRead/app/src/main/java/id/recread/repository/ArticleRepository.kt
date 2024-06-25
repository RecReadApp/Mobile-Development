package id.recread.repository

import id.recread.model.Article
import id.recread.network.NetworkService
import retrofit2.Call

class ArticleRepository(private val networkService: NetworkService) {

    suspend fun getArticles(): Call<List<Article>> {
        return networkService.getProducts()
    }

}