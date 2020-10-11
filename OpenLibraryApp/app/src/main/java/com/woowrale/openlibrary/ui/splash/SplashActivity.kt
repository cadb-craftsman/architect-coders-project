package com.woowrale.openlibrary.ui.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.os.HandlerCompat
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.ui.base.BaseActivity
import com.woowrale.openlibrary.ui.main.MainActivity
import com.woowrale.openlibrary.utils.startActivity


class SplashActivity : BaseActivity() {

    private val SPLASH_DISPLAY_LENGTH: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.e("Splash", "SplashActivity")
       HandlerCompat.postDelayed(Handler(), Runnable {
           startActivity<MainActivity>()
           this.finish()
       }, null, SPLASH_DISPLAY_LENGTH)
    }
}