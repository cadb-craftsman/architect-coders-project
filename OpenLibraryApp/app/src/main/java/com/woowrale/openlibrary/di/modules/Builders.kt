package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.ui.base.BaseActivity
import com.woowrale.openlibrary.ui.global.local.GlobalLocalFragment
import com.woowrale.openlibrary.ui.global.remote.GlobalRemoteActivity
import com.woowrale.openlibrary.ui.global.remote.GlobalRemoteFragment
import com.woowrale.openlibrary.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindGlobalRemoteActivity(): GlobalRemoteActivity
}

@Module
abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeGlobalRemoteFragment(): GlobalRemoteFragment

    @ContributesAndroidInjector
    abstract fun contributeGlobalLocalFragment(): GlobalLocalFragment
}