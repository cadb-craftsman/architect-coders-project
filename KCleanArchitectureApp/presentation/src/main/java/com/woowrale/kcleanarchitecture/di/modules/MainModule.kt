package com.woowrale.kcleanarchitecture.di.modules

import android.content.Context
import com.woowrale.data.repository.remote.RemoteContactSource
import com.woowrale.data.repository.remote.RemoteRepository
import com.woowrale.kcleanarchitecture.MainApplication
import com.woowrale.kcleanarchitecture.data.remote.datasource.GetRemoteContactSource
import com.woowrale.kcleanarchitecture.data.remote.server.ApiClient
import com.woowrale.kcleanarchitecture.data.remote.ws.ApiService
import com.woowrale.kcleanarchitecture.di.threads.JobThread
import com.woowrale.kcleanarchitecture.di.threads.UIThread
import com.woowrale.usecase.threads.JobScheduler
import com.woowrale.usecase.threads.UIScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule constructor(mApplication: MainApplication) {

    private var mainApplication: MainApplication = mApplication

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mainApplication
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiClient().getClient().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteContactSource(apiService: ApiService): RemoteContactSource {
        return GetRemoteContactSource(
            apiService
        )
    }

    @Provides
    @Singleton
    fun provideRepository(remoteContactSource: RemoteContactSource): RemoteRepository {
        return RemoteRepository(remoteContactSource)
    }

    @Provides
    @Singleton
    fun provideJobScheduler(jobThread: JobThread): JobScheduler {
        return jobThread
    }

    @Provides
    @Singleton
    fun provideUIScheduler(uiThread: UIThread): UIScheduler {
        return uiThread
    }
}
