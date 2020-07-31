package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.data.remote.server.ApiClient
import com.woowrale.openlibrary.data.remote.server.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiClient().getClient().create(ApiService::class.java)
    }
    /*
    @Provides
    @Singleton
    fun provideRemoteContactSource(apiService: ApiService): RemoteContactSource {
        return GetRemoteContactSource(apiService)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(remoteContactSource: RemoteContactSource): RemoteRepository {
        return RemoteRepository(remoteContactSource)
    }
     */
}