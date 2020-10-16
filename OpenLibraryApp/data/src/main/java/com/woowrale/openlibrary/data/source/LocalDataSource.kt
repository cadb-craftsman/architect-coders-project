package com.woowrale.openlibrary.data.source

import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed

interface LocalDataSource {

    fun searchBookByISBN(isbn: Int): List<Book>

    fun searchBookByOLID(olid: String): List<Book>

    fun searchSeedById(id: String): List<Seed>

    fun getAllSeeds(): List<Seed>

    fun saveSeed(seed: Seed): Boolean

    fun deleteSeed(seed: Seed): Boolean

    fun saveBook(book: Book): Boolean

    fun deleteBook(book: Book): Boolean
}