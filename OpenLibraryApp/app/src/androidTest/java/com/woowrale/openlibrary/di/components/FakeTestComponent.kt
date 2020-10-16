package com.woowrale.openlibrary.di.components

import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.di.modules.FakeTestAppModule
import com.woowrale.openlibrary.di.modules.ThreadModule
import com.woowrale.openlibrary.ui.global.remote.GlobalRemoteViewModel
import com.woowrale.openlibrary.ui.splash.OpenLibraryUiTest
import okhttp3.mockwebserver.MockWebServer
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeTestAppModule::class,
        ThreadModule::class
    ]
)
interface FakeTestComponent {

    val openLibraryRepository: OpenLibraryRepository
    val useCaseFactory: UseCaseFactory
    val globalRemoteViewModel: GlobalRemoteViewModel
    val mockWebServer: MockWebServer

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appTest(application: OpenLibraryUiTest): FakeTestComponent.Builder
        fun build(): FakeTestComponent
    }
}