package com.oleksandrlysun.securemessenger.presentation.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.models.User
import kotlin.math.absoluteValue

class AvatarImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ImageView(context, attrs, defStyle, defStyleRes) {

    companion object {
        private const val TEXT_AREA_WIDTH_PERCENT = .75f
        private const val TEXT_SIZE_PERCENT = .40f
    }

    private val colors = mutableListOf(R.color.colorAvatar)

    init {
        val avatarColors = resources.getIntArray(R.array.avatar_colors).toList()
        colors.addAll(avatarColors)
    }

    fun setUser(user: User) {
        val index = user.userName.hashCode().absoluteValue % colors.size
        setImageDrawable(UserAvatarDrawable(user, colors[index]))
    }

    private inner class UserAvatarDrawable(user: User, backgroundColor: Int) : Drawable() {

        private val backgroundPaint = Paint().apply {
            color = backgroundColor
            isAntiAlias = true
        }

        private val textPaint = TextPaint().apply {
            color = ContextCompat.getColor(context, R.color.colorTextInverted)
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        private val text = getText(user.firstName, user.lastName)
        private var textLayout: StaticLayout? = null

        override fun onBoundsChange(bounds: Rect) {
            super.onBoundsChange(bounds)
            refreshTextLayout()
        }

        override fun draw(canvas: Canvas) {
            drawBackground(canvas)
            drawText(canvas)
        }

        override fun getOpacity(): Int {
            return PixelFormat.TRANSLUCENT
        }

        private fun drawBackground(canvas: Canvas) {
            val circleX = bounds.width() / 2f
            val circleY = bounds.height() / 2f
            canvas.drawCircle(circleX, circleY, circleX, backgroundPaint)
        }

        private fun drawText(canvas: Canvas) {
            canvas.save()
            val textLayout = getTextLayout()
            val canvasTranslationX = bounds.width() / 2f - textLayout.width / 2f
            val canvasTranslationY = bounds.height() / 2f - textLayout.height / 2f
            canvas.translate(canvasTranslationX, canvasTranslationY)
            textLayout.draw(canvas)
            canvas.restore()
        }

        private fun getText(firstName: String?, lastName: String?): String {
            return arrayOf(firstName, lastName)
                .filterNot { it.isNullOrEmpty() }
                .map { it?.first() }
                .joinToString(separator = "")
        }

        private fun getTextAreaWidth(): Int {
            return (bounds.width() * TEXT_AREA_WIDTH_PERCENT).toInt()
        }

        private fun getTextLayout(): StaticLayout {
            if (textLayout == null) {
                refreshTextLayout()
            }
            return textLayout!!
        }

        private fun refreshTextLayout() {
            textPaint.textSize = bounds.height() * TEXT_SIZE_PERCENT
            textLayout = StaticLayout(text, textPaint, getTextAreaWidth(), Layout.Alignment.ALIGN_CENTER, 1f, 0f, false)
        }

        override fun setAlpha(alpha: Int) {
            backgroundPaint.alpha = alpha
            textPaint.alpha = alpha
        }

        override fun setColorFilter(colorFilter: ColorFilter?) {
            backgroundPaint.colorFilter = colorFilter
            textPaint.colorFilter = colorFilter
        }
    }
}