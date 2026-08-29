package com.imuxuan.floatingview

import android.app.Activity
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * Minimal local drop-in replacement for com.imuxuan.floatingview.FloatingView.
 * Singleton that shows the custom FloatingMagnetView as a system overlay (TYPE_APPLICATION_OVERLAY).
 */
class FloatingView private constructor() {

    private var customView: FloatingMagnetView? = null
    private var added = false

    fun customView(view: View) {
        customView = view as? FloatingMagnetView
    }

    val view: FloatingMagnetView?
        get() = customView

    @Synchronized
    fun attach(activity: Activity) {
        val v = customView ?: return
        if (added) return
        val ctx = activity.applicationContext
        val wm = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                @Suppress("DEPRECATION")
                WindowManager.LayoutParams.TYPE_PHONE
            },
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        )
        params.gravity = Gravity.START or Gravity.CENTER_VERTICAL
        params.x = 0
        params.y = 0
        v.windowManager = wm
        v.windowLayoutParams = params
        try {
            wm.addView(v, params)
            added = true
        } catch (e: Exception) {
            added = false
        }
    }

    @Synchronized
    fun detach(activity: Activity) {
        val ctx = activity.applicationContext
        val wm = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val v = customView ?: run { added = false; return }
        if (added) {
            try {
                wm.removeView(v)
            } catch (_: Exception) {
            }
            added = false
        }
    }

    companion object {
        private val instance by lazy { FloatingView() }
        fun get(): FloatingView = instance
    }
}
