package com.woowrale.openlibrary.di.modules

import android.content.Context
import com.woowrale.openlibrary.data.local.database.OpenLibraryDatabase

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): OpenLibraryDatabase {
        return OpenLibraryDatabase.build(context)
    }
    /*
    @Provides
    fun provideLocalContactDataSource(contactDatabase: ContactDatabase): LocalContactSource {
        return GetLocalContactSource(contactDatabase)
    }

    @Provides
    fun provideLocalRepository(localDataSource: LocalContactSource): LocalRepository {
        return LocalRepository(localDataSource)
    }
    */
}