package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.data.FakeLocalDataSource
import com.woowrale.openlibrary.data.FakeRemoteDataSource
import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.data.source.LocalDataSource
import com.woowrale.openlibrary.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeAppModule {

    @Provides
    @Singleton
    fun localDataSourceProvider(): LocalDataSource = FakeLocalDataSource()

    @Provides
    @Singleton
    fun remoteDataSourceProvider(): RemoteDataSource = FakeRemoteDataSource()

    @Provides
    @Singleton
    fun provideOpenLibraryRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): OpenLibraryRepository {
        return OpenLibraryRepository(localDataSource, remoteDataSource)
    }
}