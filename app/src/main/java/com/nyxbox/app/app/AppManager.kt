package com.nyxbox.app.app

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.nyxbox.app.view.main.NyxBoxLoader


object AppManager {
    private const val TAG = "AppManager"

    @JvmStatic
    val mNyxBoxLoader by lazy {
        try {
            NyxBoxLoader()
        } catch (e: Exception) {
            Log.e(TAG, "Error creating NyxBoxLoader: ${e.message}")

            NyxBoxLoader() 
        }
    }

    @JvmStatic
    val mNyxBoxCore by lazy {
        try {
            mNyxBoxLoader.getNyxBoxCore()
        } catch (e: Exception) {
            Log.e(TAG, "Error getting NyxBoxCore: ${e.message}")
            throw e 
        }
    }

    @JvmStatic
    val mRemarkSharedPreferences: SharedPreferences by lazy {
        try {
            App.getContext().getSharedPreferences("UserRemark", Context.MODE_PRIVATE)
        } catch (e: Exception) {
            Log.e(TAG, "Error creating SharedPreferences: ${e.message}")
            throw e 
        }
    }

    fun doAttachBaseContext(context: Context) {
        try {
            mNyxBoxLoader.attachBaseContext(context)
            mNyxBoxLoader.addLifecycleCallback()
        } catch (e: Exception) {
            Log.e(TAG, "Error in doAttachBaseContext: ${e.message}")
            
        }
    }

    fun doOnCreate(context: Context) {
        try {
            mNyxBoxLoader.doOnCreate(context)
            initThirdService(context)
        } catch (e: Exception) {
            Log.e(TAG, "Error in doOnCreate: ${e.message}")
            
        }
    }

    private fun initThirdService(context: Context) {
        try {
            
            
        } catch (e: Exception) {
            Log.e(TAG, "Error in initThirdService: ${e.message}")
        }
    }
}
