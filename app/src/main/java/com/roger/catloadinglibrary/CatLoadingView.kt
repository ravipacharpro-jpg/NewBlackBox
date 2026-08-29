package com.roger.catloadinglibrary

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment

/**
 * Minimal local drop-in replacement for com.roger.catloadinglibrary.CatLoadingView.
 * A simple DialogFragment showing an indeterminate progress spinner (the original showed a
 * cat animation). Exposes the same API used by LoadingActivity.
 */
class CatLoadingView : DialogFragment() {

    private var cancelAble = true
    private var bgColorRes: Int = android.R.color.white

    fun setBackgroundColor(colorRes: Int) {
        bgColorRes = colorRes
    }

    fun setClickCancelAble(cancel: Boolean) {
        cancelAble = cancel
        isCancelable = cancel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): android.app.Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(cancelAble)
        val context = requireContext()
        val density = context.resources.displayMetrics.density
        val size = (density * 64).toInt()
        val progress = ProgressBar(context).apply {
            isIndeterminate = true
        }
        val lp = FrameLayout.LayoutParams(size, size).apply { gravity = Gravity.CENTER }
        val container = FrameLayout(context).apply {
            setPadding((size * 0.4f).toInt(), (size * 0.4f).toInt(), (size * 0.4f).toInt(), (size * 0.4f).toInt())
            try {
                setBackgroundColor(context.getColor(bgColorRes))
            } catch (_: Exception) {
                setBackgroundColor(Color.WHITE)
            }
            addView(progress, lp)
        }
        dialog.setContentView(container)
        return dialog
    }
}
