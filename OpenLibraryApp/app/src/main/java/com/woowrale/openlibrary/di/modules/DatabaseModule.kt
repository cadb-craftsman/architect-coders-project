package com.woowrale.openlibrary.di.modules

import android.content.Context
import com.woowrale.openlibrary.data.local.LocalOpenLibrarySource
import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.local.database.OpenLibraryDatabase
import com.woowrale.openlibrary.data.local.datasource.GetLocalOpenLibrarySource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideContext(context: Context): OpenLibraryDatabase {
        return OpenLibraryDatabase.build(context)
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