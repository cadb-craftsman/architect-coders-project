package com.woowrale.openlibrary.data.remote.datasource

import com.woowrale.openlibrary.BuildConfig
import com.woowrale.openlibrary.data.remote.RemoteOpenLibrarySource
import com.woowrale.openlibrary.data.remote.mappers.toSeed
import com.woowrale.openlibrary.data.remote.server.ApiService
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Menu
import com.woowrale.openlibrary.domain.model.Seed
import java.io.IOException

class GetRemoteOpenLibrarySource(apiService: ApiService) :  RemoteOpenLibrarySource {

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
        return try {
            val fullUrl = BuildConfig.BASE_URL + BuildConfig.API_LIST + id + BuildConfig.SEEDS_URL
            apiService.getSeedList(fullUrl).execute().body()!!.entries.map { it.toSeed() }
        } catch (e: IOException) {
            return listOfNotNull()
        }

    }


}

