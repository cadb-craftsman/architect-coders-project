package com.woowrale.openlibrary.data.local.database

import androidx.room.*
import com.woowrale.openlibrary.data.local.model.BookEntity

@Dao
interface OpenLibraryDao {

    @Query("SELECT * FROM books")
    fun getAll(): List<BookEntity>

    @Query("SELECT * FROM books WHERE id = :id")
    fun findById(id: Int): BookEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(book: BookEntity)

    @Update
    fun deleteBook(book: BookEntity)
}