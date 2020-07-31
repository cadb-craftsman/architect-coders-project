package com.woowrale.openlibrary.di.components

import com.woowrale.openlibrary.di.modules.LocalModule
import com.woowrale.openlibrary.di.modules.MainModule
import com.woowrale.openlibrary.di.modules.RemoteModule
import com.woowrale.openlibrary.ui.base.BaseActivity
import com.woowrale.openlibrary.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, RemoteModule::class, LocalModule::class])
interface MainComponent {

    fun inject(activity: BaseActivity)

    fun inject(activity: MainActivity)

}