package com.woowrale.openlibrary.data.repository

import com.woowrale.openlibrary.data.source.LocalDataSource
import com.woowrale.openlibrary.data.source.RemoteDataSource
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed

class OpenLibraryRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun searchBook(olid: String, env: String): List<Book> {
        if(env.equals("REMOTE")){
            return remoteDataSource.searchBookByOLID(olid)
        }else{
            return localDataSource.searchBookByOLID(olid)
        }
    }

    fun searchSeed(id: String, env: String): List<Seed> {
        if(env.equals("REMOTE")){
            return remoteDataSource.searchSeedById(id)
        }else{
            return localDataSource.getAllSeeds()
        }
    }

    fun saveSeed(seed: Seed): Boolean {
        remoteDataSource.searchBookByOLID(seed.olid).let {
            if (!it.isNullOrEmpty()) {
                localDataSource.saveBook(it[0])
            }
        }
        return localDataSource.saveSeed(seed)
    }

    fun deleteSeed(seed: Seed): Boolean {
        localDataSource.searchBookByOLID(seed.olid).let {
            if(!it.isNullOrEmpty()){
                localDataSource.deleteBook(it[0])
            }
        }
        return localDataSource.deleteSeed(seed)
    }

    fun saveBook(book: Book): Boolean {
        return localDataSource.saveBook(book)
    }

}