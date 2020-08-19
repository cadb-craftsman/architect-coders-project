package com.woowrale.openlibrary.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.woowrale.openlibrary.BuildConfig
import com.woowrale.openlibrary.data.local.model.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class OpenLibraryDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
                context,
                OpenLibraryDatabase::class.java,
                BuildConfig.DATABASE_NAME)
                .build()
    }

    abstract fun openLibraryDao(): OpenLibraryDao
}