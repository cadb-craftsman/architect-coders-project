package com.woowrale.openlibrary.di.modules

import com.woowrale.openlibrary.di.threads.JobThread
import com.woowrale.openlibrary.di.threads.UIThread
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ThreadModule() {

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
