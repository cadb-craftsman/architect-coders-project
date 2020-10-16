package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.data.FakeTestLocalDataSource
import com.woowrale.openlibrary.data.FakeTestRemoteDataSource
import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.data.source.LocalDataSource
import com.woowrale.openlibrary.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeTestAppModule {

    @Provides
    @Singleton
    fun localDataSourceProvider(): LocalDataSource = FakeTestLocalDataSource()

    /*
    @Provides
    @Singleton
    fun provideApiService( @Named("baseUrl") baseUrl: String): ApiService {
        return ApiClient().getClient().create(ApiService::class.java)
    }
     */

    @Provides
    @Singleton
    fun remoteDataSourceProvider(): RemoteDataSource = FakeTestRemoteDataSource()

    @Provides
    @Singleton
    fun provideOpenLibraryRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): OpenLibraryRepository {
        return OpenLibraryRepository(localDataSource, remoteDataSource)
    }
}