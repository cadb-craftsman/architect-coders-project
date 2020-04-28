package com.woowrale.kcleanarchitecture.di.components

import com.woowrale.kcleanarchitecture.di.modules.MainModule
import com.woowrale.kcleanarchitecture.ui.base.BaseActivity
import com.woowrale.kcleanarchitecture.ui.search.home.SearchActivity
import com.woowrale.kcleanarchitecture.ui.search.local.LocalSearchActivity
import com.woowrale.kcleanarchitecture.ui.search.remote.RemoteSearchActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: BaseActivity)

    fun inject(activity: SearchActivity)

    fun inject(activity: LocalSearchActivity)

    fun inject(activity: RemoteSearchActivity)

}