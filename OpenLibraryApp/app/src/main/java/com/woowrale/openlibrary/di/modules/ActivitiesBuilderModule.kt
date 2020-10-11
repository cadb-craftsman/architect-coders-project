package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.ui.base.BaseActivity
import com.woowrale.openlibrary.ui.main.MainActivity
import com.woowrale.openlibrary.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}