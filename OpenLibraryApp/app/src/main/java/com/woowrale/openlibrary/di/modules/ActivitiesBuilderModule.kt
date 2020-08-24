package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.ui.base.BaseActivity
import com.woowrale.openlibrary.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}