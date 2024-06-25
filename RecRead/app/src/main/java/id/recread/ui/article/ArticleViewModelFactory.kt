package id.recread.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.recread.repository.ArticleRepository

class ArticleViewModelFactory(
    private val articleRepository: ArticleRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(articleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}