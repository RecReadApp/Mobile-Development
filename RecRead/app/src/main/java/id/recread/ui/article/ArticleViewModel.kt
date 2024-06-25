package id.recread.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.recread.model.Article
import id.recread.repository.ArticleRepository

class ArticleViewModel(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _state = MutableLiveData<List<Article>>()
    val state: LiveData<List<Article>> = _state

    private var articles: List<Article> = emptyList()

    init {
        getArticles()
    }

    private fun getArticles() {
        val sampleArticles = listOf(
            Article(
                1,
                "Merahnya Merah (Paperback)",
                "Iwan Simatupang",
                "(Shelved 1 time as 01-novel-Indo)",
                "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1463774180i/1606784.jpg",
                "3.99",
                502,
                "1968"
            ),
            Article(
                1,
                "Anak Bajang Menggiring Angin (Paperback)",
                "Iwan Simatupang",
                "(Shelved 1 time as 01-novel-Indo)",
                "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1416983455i/1439798.jpg",
                "3.99",
                502,
                "1968"
            ),
            Article(
                1,
                "Burung - Burung Manyar (Paperback)",
                "Iwan Simatupang",
                "(Shelved 1 time as 01-novel-Indo)",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPksMF93IAm63QwRkAMRbOL8cPVIuK9ZjWxw&s",
                "3.99",
                502,
                "1968"
            )
        )

        articles = sampleArticles
        _state.value = articles

        /* TODO: Uncomment below code if want you to get data from API */
        /*viewModelScope.launch {
            repository.getArticles().enqueue(object : Callback<List<Article>> {
                override fun onResponse(
                    call: Call<List<Article>>,
                    response: Response<List<Article>>
                ) {
                    if (response.isSuccessful) {
                        articles = response.body()
                        _state.value = response.body()
                    } else {
                        _state.value = emptyList()
                    }
                }

                override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                    Log.e("ArticleViewModel", t.localizedMessage ?: "")
                }
            })
        }*/
    }

    fun searchArticle(query: String) {
        if (query.isEmpty()) {
            _state.value = articles
        } else {
            val searchedArticle = articles.filter {
                it.title.contains(query, ignoreCase = true)
            }
            _state.value = searchedArticle
        }

    }

}