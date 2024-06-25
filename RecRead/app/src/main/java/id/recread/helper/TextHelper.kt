package id.recread.helper

import android.text.SpannableString
import android.text.style.UnderlineSpan

object TextHelper {

    fun underlineText(text: String, end: Int, start: Int = 0): SpannableString {
        val result = SpannableString(text)
        result.setSpan(UnderlineSpan(), start, end, 0)
        return result
    }

}