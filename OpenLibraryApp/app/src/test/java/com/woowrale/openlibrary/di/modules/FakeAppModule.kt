package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.data.FakeLocalDataSource
import com.woowrale.openlibrary.data.FakeRemoteDataSource
import com.woowrale.openlibrary.data.local.LocalOpenLibrarySource
import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeAppModule {

    @Provides
    @Singleton
    fun localDataSourceProvider(): LocalOpenLibrarySource = FakeLocalDataSource()

    @Provides
    fun provideLocalOpenLibraryRepository(localOpenLibrarySource: LocalOpenLibrarySource): LocalRepository {
        return LocalRepository(localOpenLibrarySource)
    }

    @Provides
    @Singleton
    fun remoteDataSourceProvider(): RemoteOpenLibrarySource = FakeRemoteDataSource()

    @Provides
    @Singleton
    fun provideRemoteRepository(remoteOpenLibrarySource: RemoteOpenLibrarySource): RemoteRepository {
        return RemoteRepository(remoteOpenLibrarySource)
    }
}