package com.woowrale.openlibrary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.woowrale.openlibrary.R
import com.woowrale.openlibrary.ui.dialogs.AboutMessageDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    fun showAboutMessageDialog(){
        AboutMessageDialog.newInstance()
            .show(this.supportFragmentManager, "About Message Dialog")
    }
}
