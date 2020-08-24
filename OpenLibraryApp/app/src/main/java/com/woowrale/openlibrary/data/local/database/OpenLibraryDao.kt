package com.woowrale.openlibrary.data.local.database

import androidx.room.*
import com.woowrale.openlibrary.data.local.model.BookEntity
import com.woowrale.openlibrary.data.local.model.SeedEntity

@Dao
interface OpenLibraryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(bookEntity: BookEntity)

    @Query("SELECT * FROM books WHERE bibKey = :id")
    fun findBookById(id: String): List<BookEntity>

    @Query("DELETE FROM books WHERE bibKey = :id")
    fun deleteBook(id: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSeed(seedEntity: SeedEntity)

    @Query("DELETE FROM seeds WHERE olid = :id")
    fun deleteSeed(id: String)

    @Query("SELECT * FROM seeds")
    fun getAllSeeds(): List<SeedEntity>

    @Query("SELECT * FROM seeds WHERE olid = :id")
    fun findSeedById(id: String): List<SeedEntity>

}