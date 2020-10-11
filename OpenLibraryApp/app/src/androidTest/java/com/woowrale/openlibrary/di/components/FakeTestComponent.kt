package com.woowrale.openlibrary.di.components

import com.woowrale.openlibrary.OpenLibraryTest
import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.di.modules.FakeAppModule
import com.woowrale.openlibrary.di.modules.ThreadModule
import com.woowrale.openlibrary.ui.details.DetailsViewModel
import com.woowrale.openlibrary.ui.global.local.GlobalLocalViewModel
import com.woowrale.openlibrary.ui.global.remote.GlobalRemoteViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeAppModule::class,
        ThreadModule::class
    ]
)
interface FakeTestComponent {

    val localRepository: LocalRepository
    val remoteRepository: RemoteRepository
    val useCaseFactory: UseCaseFactory
    val globalRemoteViewModel: GlobalRemoteViewModel
    val globalLocalViewModel: GlobalLocalViewModel
    val detailsViewModel: DetailsViewModel

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appTest(application: OpenLibraryTest): FakeTestComponent.Builder
        fun build(): FakeTestComponent
    }
}