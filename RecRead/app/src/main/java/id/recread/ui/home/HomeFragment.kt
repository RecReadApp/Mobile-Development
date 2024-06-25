package id.recread.ui.home

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.recread.R
import id.recread.databinding.FragmentHomeBinding
import id.recread.helper.TextHelper

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        underlineTextViewAll()

        binding?.let {
            Glide.with(this)
                .load(R.drawable.image_fiction)
                .transform(RoundedCorners(32))
                .into(it.imageFiction)

            Glide.with(this)
                .load(R.drawable.image_non_fiction)
                .transform(RoundedCorners(32))
                .into(it.imageNonFiction)
        }

    }

    private fun underlineTextViewAll() {
        val length = getString(R.string.home_view_all).length
        val underlinedText = TextHelper.underlineText(getString(R.string.home_view_all), length)

        binding?.textViewAllPopularTopics?.text = underlinedText
        binding?.textViewAllCategories?.text = underlinedText
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}