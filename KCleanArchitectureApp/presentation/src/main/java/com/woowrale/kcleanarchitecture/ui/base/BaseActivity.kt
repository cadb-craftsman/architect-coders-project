package com.woowrale.kcleanarchitecture.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.woowrale.kcleanarchitecture.MainApplication
import com.woowrale.kcleanarchitecture.di.components.MainComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDaggerMainComponent().inject(this)
    }

    protected fun getDaggerMainComponent(): MainComponent {
        val mainApplication = MainApplication()
        mainApplication.onCreate()
        return mainApplication.mainComponent
    }

    protected abstract fun initDagger()
}
