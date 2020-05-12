package devs.goldenpie.com.linker

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.view.View
import android.widget.TextView

private fun String.findLinkPositions(): List<Pair<Int, Int>> {
    val matcher = Patterns.WEB_URL.matcher(this)
    val list = mutableListOf<Pair<Int, Int>>()

    while (Patterns.WEB_URL.matcher(this).find()) {
        list.add(matcher.start(1) to matcher.end())
    }

    return list
}

private fun String.spanLink(color: Int, boldLink: Boolean = false, onClick: (url: String) -> Unit): SpannableString {
    val ss = SpannableString(this)

    with(findLinkPositions()) {
        if (isNotEmpty())
            forEach { pair ->
                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(textView: View) {
                        onClick(substring(pair.first, pair.second))
                    }
                }

                ss.apply {
                    setSpan(clickableSpan, pair.first, pair.second, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    setSpan(ForegroundColorSpan(color), pair.first, pair.second, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    setSpan(UnderlineSpan(), pair.first, pair.second, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                    if (boldLink) {
                        setSpan(Typeface.BOLD, pair.first, pair.second, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }
            }
    }

    return ss
}

fun TextView.spanAllLinks(color: Int, boldLink: Boolean = false, onLinkClick: (url: String) -> Unit): TextView {
    return apply {
        with(text.toString().spanLink(color, boldLink, onLinkClick)) {
            if (getSpans(0, length, UnderlineSpan::class.java).isNotEmpty()) {
                text = this
                movementMethod = LinkMovementMethod.getInstance()
                isClickable = true
            }
        }
    }
}