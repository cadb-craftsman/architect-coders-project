package com.woowrale.openlibrary.di.components

import com.woowrale.openlibrary.OpenLibraryRepositoryTest
import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.di.modules.FakeAppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        FakeAppModule::class]
)
interface FakeRepositoryComponent {
    val openLibraryRepository: OpenLibraryRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appTest(application: OpenLibraryRepositoryTest): FakeRepositoryComponent.Builder
        fun build(): FakeRepositoryComponent
    }
}