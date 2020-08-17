package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.data.remote.RemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.data.remote.datasource.GetRemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.server.ApiClient
import com.woowrale.openlibrary.data.remote.server.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiClient().getClient().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteOpenLibrary(apiService: ApiService): RemoteOpenLibrarySource {
        return GetRemoteOpenLibrarySource(apiService)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(remoteOpenLibrarySource: RemoteOpenLibrarySource): RemoteRepository {
        return RemoteRepository(remoteOpenLibrarySource)
    }
}