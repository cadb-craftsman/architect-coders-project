package com.woowrale.openlibrary.di.modules

import android.app.Application
import com.woowrale.openlibrary.data.local.LocalOpenLibrarySource
import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.local.database.OpenLibraryDatabase
import com.woowrale.openlibrary.data.local.datasource.GetLocalOpenLibrarySource
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Inject
    lateinit var application: Application

    @Provides
    @Singleton
    fun provideContext(application: Application): OpenLibraryDatabase {
        return OpenLibraryDatabase.build(application.applicationContext)
    }

    @Provides
    fun provideLocalOpenLibraryDataSource(openLibraryDatabase: OpenLibraryDatabase): LocalOpenLibrarySource {
        return GetLocalOpenLibrarySource(openLibraryDatabase)
    }

    @Provides
    fun provideLocalOpenLibraryRepository(localOpenLibrarySource: LocalOpenLibrarySource): LocalRepository {
        return LocalRepository(localOpenLibrarySource)
    }
}