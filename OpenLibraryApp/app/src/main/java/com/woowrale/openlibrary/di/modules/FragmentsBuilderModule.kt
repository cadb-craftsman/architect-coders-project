package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.ui.details.DetailsFragment
import com.woowrale.openlibrary.ui.global.local.GlobalLocalFragment
import com.woowrale.openlibrary.ui.global.remote.GlobalRemoteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeGlobalRemoteFragment(): GlobalRemoteFragment

    @ContributesAndroidInjector
    abstract fun contributeGlobalLocalFragment(): GlobalLocalFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment
}