package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.data.FakeTestLocalDataSource
import com.woowrale.openlibrary.data.FakeTestRemoteDataSource
import com.woowrale.openlibrary.data.local.LocalOpenLibrarySource
import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeTestAppModule {

    @Provides
    @Singleton
    fun localDataSourceProvider(): LocalOpenLibrarySource = FakeTestLocalDataSource()

    @Provides
    fun provideLocalOpenLibraryRepository(localOpenLibrarySource: LocalOpenLibrarySource): LocalRepository {
        return LocalRepository(localOpenLibrarySource)
    }

    /*
    @Provides
    @Singleton
    fun provideApiService( @Named("baseUrl") baseUrl: String): ApiService {
        return ApiClient().getClient().create(ApiService::class.java)
    }
     */

    @Provides
    @Singleton
    fun remoteDataSourceProvider(): RemoteOpenLibrarySource = FakeTestRemoteDataSource()

    @Provides
    @Singleton
    fun provideRemoteRepository(remoteOpenLibrarySource: RemoteOpenLibrarySource): RemoteRepository {
        return RemoteRepository(remoteOpenLibrarySource)
    }
}