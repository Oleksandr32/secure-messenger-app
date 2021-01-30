package com.oleksandrlysun.securemessenger.presentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    companion object {
        private const val MARGIN_START_DP = 70
    }

    private val attrs = intArrayOf(android.R.attr.listDivider)
    private var divider: Drawable? = null
    private var scale: Float = 0.0f

    init {
        val styledAttributes = context.obtainStyledAttributes(attrs)
        divider = styledAttributes.getDrawable(0)
        scale = context.resources.displayMetrics.density
        styledAttributes.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = (MARGIN_START_DP * scale + 0.5f).toInt()
        val right = parent.width

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
        }
    }
}