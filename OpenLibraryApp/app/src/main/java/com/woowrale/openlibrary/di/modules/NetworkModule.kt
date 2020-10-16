package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.data.remote.datasource.RemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.server.ApiClient
import com.woowrale.openlibrary.data.remote.server.ApiService
import com.woowrale.openlibrary.data.source.RemoteDataSource
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
    fun provideRemoteOpenLibrary(apiService: ApiService): RemoteDataSource {
        return RemoteOpenLibrarySource(apiService)
    }
}