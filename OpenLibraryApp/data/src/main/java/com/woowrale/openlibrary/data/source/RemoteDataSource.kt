package com.woowrale.openlibrary.data.source

import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed

interface RemoteDataSource {

    fun searchBookByISBN(isbn: Int): List<Book>

    fun searchBookByOLID(olid: String): List<Book>

    fun searchSeedById(id: String): List<Seed>
}