package com.woowrale.openlibrary.di.components

import com.woowrale.openlibrary.OpenLibraryUseCaseTest
import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.di.factory.UseCaseFactory
import com.woowrale.openlibrary.di.modules.FakeAppModule
import com.woowrale.openlibrary.di.modules.ThreadModule
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
interface FakeUseCaseComponent {

    val openLibraryRepository: OpenLibraryRepository
    val useCaseFactory: UseCaseFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appTest(application: OpenLibraryUseCaseTest): FakeUseCaseComponent.Builder
        fun build(): FakeUseCaseComponent
    }
}