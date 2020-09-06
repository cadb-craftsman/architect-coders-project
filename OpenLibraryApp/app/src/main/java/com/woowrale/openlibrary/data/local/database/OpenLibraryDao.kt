package com.woowrale.openlibrary.data.local.database

import androidx.room.*
import com.woowrale.openlibrary.data.local.model.BookEntity
import com.woowrale.openlibrary.data.local.model.SeedEntity

@Dao
interface OpenLibraryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(bookEntity: BookEntity): Long

    @Query("SELECT * FROM books WHERE bibKey = :id")
    fun findBookById(id: String): List<BookEntity>

    @Query("DELETE FROM books WHERE bibKey = :id")
    fun deleteBook(id: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSeed(seedEntity: SeedEntity): Long

    @Query("DELETE FROM seeds WHERE olid = :id")
    fun deleteSeed(id: String): Int

    @Query("SELECT * FROM seeds")
    fun getAllSeeds(): List<SeedEntity>

    @Query("SELECT * FROM seeds WHERE olid = :id")
    fun findSeedById(id: String): List<SeedEntity>

}