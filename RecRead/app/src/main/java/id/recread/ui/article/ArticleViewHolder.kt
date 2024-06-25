package id.recread.ui.article

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.recread.R
import id.recread.databinding.LayoutArticleItemBinding
import id.recread.helper.TextHelper
import id.recread.model.Article

class ArticleViewHolder(private val binding: LayoutArticleItemBinding) : ViewHolder(binding.root) {

    fun onBind(article: Article) {
        val underlinedTitle = TextHelper.underlineText(
            text = article.title,
            start = 0,
            end = article.title.length
        )
        val length =
            binding.root.context.getString(R.string.article_author_name, article.author).length
        val underlinedAuthorName = TextHelper.underlineText(
            text = binding.root.context.getString(
                R.string.article_author_name,
                article.author
            ),
            start = 3,
            end = length
        )

        binding.textTitle.text = underlinedTitle
        binding.textAuthor.text = underlinedAuthorName

        binding.textPublisher.text = article.publisher
        binding.textRating.text = binding.root.context.getString(
            R.string.article_rating,
            article.avgRating,
            article.totalRating,
            article.publishedDate
        )
        Glide.with(binding.root.context)
            .load(article.posterUrl)
            .transform(CenterCrop(), RoundedCorners(32))
            .into(binding.imagePoster)
    }

}