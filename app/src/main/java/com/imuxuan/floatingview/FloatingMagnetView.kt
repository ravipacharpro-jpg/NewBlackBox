package com.imuxuan.floatingview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * Minimal local drop-in replacement for com.imuxuan.floatingview.FloatingMagnetView.
 * Provides a draggable FrameLayout that can be hosted by FloatingView as a system overlay.
 */
open class FloatingMagnetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    var windowManager: WindowManager? = null
    var windowLayoutParams: WindowManager.LayoutParams? = null

    private var lastX = 0f
    private var lastY = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return super.onTouchEvent(event)
        val wm = windowManager
        val params = windowLayoutParams
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.rawX
                lastY = event.rawY
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (wm != null && params != null) {
                    params.x += (event.rawX - lastX).toInt()
                    params.y += (event.rawY - lastY).toInt()
                    lastX = event.rawX
                    lastY = event.rawY
                    wm.updateViewLayout(this, params)
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (wm != null && params != null) {
                    val screenWidth = context.resources.displayMetrics.widthPixels
                    params.x = if (params.x + width / 2 < screenWidth / 2) 0 else screenWidth - width
                    wm.updateViewLayout(this, params)
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}
