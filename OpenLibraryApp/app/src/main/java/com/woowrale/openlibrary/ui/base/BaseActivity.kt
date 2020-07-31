package com.woowrale.openlibrary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.woowrale.openlibrary.MainApplication
import com.woowrale.openlibrary.di.components.MainComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDaggerMainComponent().inject(this)
    }

    protected fun getDaggerMainComponent(): MainComponent {
        val mainApplication: MainApplication = application as MainApplication
        return mainApplication.mainComponent
    }

    protected abstract fun initDagger()
}
