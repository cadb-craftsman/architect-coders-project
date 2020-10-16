package com.woowrale.openlibrary.di.modules

import android.app.Application
import com.woowrale.openlibrary.data.local.database.OpenLibraryDatabase
import com.woowrale.openlibrary.data.local.datasource.LocalOpenLibrarySource
import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.data.source.LocalDataSource
import com.woowrale.openlibrary.data.source.RemoteDataSource
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
    fun provideLocalOpenLibraryDataSource(openLibraryDatabase: OpenLibraryDatabase): LocalDataSource {
        return LocalOpenLibrarySource(openLibraryDatabase)
    }

    @Provides
    @Singleton
    fun provideOpenLibraryRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): OpenLibraryRepository {
        return OpenLibraryRepository(localDataSource, remoteDataSource)
    }

}