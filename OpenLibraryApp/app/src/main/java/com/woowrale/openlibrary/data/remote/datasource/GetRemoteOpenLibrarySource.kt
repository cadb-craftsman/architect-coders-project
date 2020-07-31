package com.woowrale.openlibrary.data.remote.datasource

import com.woowrale.openlibrary.data.remote.RemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.server.ApiService
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Menu
import com.woowrale.openlibrary.domain.model.Seed

class GetRemoteOpenLibrarySource(apiService: ApiService) : RemoteOpenLibrarySource {

    private val apiService: ApiService = apiService

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMenuList(): List<Menu> {
        TODO("Not yet implemented")
    }

    override fun searchBookByISBN(isbn: Int): List<Book> {
        TODO("Not yet implemented")
    }

    override fun searhBookByOLID(olid: String): List<Book> {
        TODO("Not yet implemented")
    }

    override fun searchSeedById(id: String): List<Seed> {
        TODO("Not yet implemented")
    }


}

