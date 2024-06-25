package id.recread.model

data class Article(
    val id: Int,
    val title: String,
    val author: String,
    val publisher: String,
    val posterUrl: String,
    val avgRating: String,
    val totalRating: Int,
    val publishedDate: String
)
