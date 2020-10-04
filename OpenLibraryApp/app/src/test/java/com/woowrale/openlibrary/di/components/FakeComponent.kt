package com.woowrale.openlibrary.di.components

import com.woowrale.openlibrary.OpenLibraryTest
import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.di.modules.FakeAppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeAppModule::class
    ]
)
interface FakeComponent{

    val localRepository: LocalRepository
    val remoteRepository: RemoteRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appTest(application: OpenLibraryTest): FakeComponent.Builder
        fun build(): FakeComponent
    }
}