package com.woowrale.openlibrary.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.woowrale.openlibrary.data.remote.model.response.BookEntriesResponse
import com.woowrale.openlibrary.data.remote.model.response.BookResponse
import com.woowrale.openlibrary.data.remote.model.response.MenuEntriesResponse
import com.woowrale.openlibrary.data.remote.model.response.SeedEntriesResponse
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Menu
import com.woowrale.openlibrary.domain.model.Seed
import java.io.IOException

class DataWrapper {

    fun fromMenuJson(s: String): MenuEntriesResponse {
        val listType = object : TypeToken<MenuEntriesResponse?>() {}.type
        return Gson().fromJson<MenuEntriesResponse>(s, listType)
    }

    fun fromSeedJson(s: String): SeedEntriesResponse {
        val listType = object : TypeToken<SeedEntriesResponse?>() {}.type
        return Gson().fromJson<SeedEntriesResponse>(s, listType)
    }

    fun fromBookJson(s: String, bookId: String): BookResponse {
        val listType = object : TypeToken<Map<String?, BookResponse?>?>() {}.type
        val bookMap = Gson().fromJson<Map<String?, BookResponse?>>(s, listType)
        return bookMap.get(bookId)!!
    }

    fun fromBookJson(s: String): BookEntriesResponse {
        val listType = object : TypeToken<BookEntriesResponse?>() {}.type
        return Gson().fromJson<BookEntriesResponse>(s, listType)
    }

    fun fromBooksJson(s: String): List<Book> {
        val listType = object : TypeToken<ArrayList<Book?>?>() {}.type
        return Gson().fromJson<ArrayList<Book>>(s, listType)
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

    fun getJsonFromAssets(fileName: String, context: Context): String {
        val assetManager = context.assets
        var json: String = ""
        try {
            val stream = assetManager.open(fileName)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            json = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

    companion object {
        fun getMenuFromJson(fileName: String, context: Context): MenuEntriesResponse {
            val wrapper = DataWrapper()
            return wrapper.fromMenuJson(wrapper.getJsonFromAssets(fileName, context))
        }

        fun getSeedFromJson(fileName: String, context: Context): SeedEntriesResponse {
            val wrapper = DataWrapper()
            return wrapper.fromSeedJson(wrapper.getJsonFromAssets(fileName, context))
        }

        fun getBookFromJson(fileName: String, bookId: String, context: Context): BookResponse {
            val wrapper = DataWrapper()
            return wrapper.fromBookJson(wrapper.getJsonFromAssets(fileName, context), bookId)
        }

        fun getSeedFromJson(fileContent: String): SeedEntriesResponse {
            val wrapper = DataWrapper()
            return wrapper.fromSeedJson(fileContent)
        }

        fun getBookFromJson(fileContent: String, bookId: String): BookResponse {
            val wrapper = DataWrapper()
            return wrapper.fromBookJson(fileContent, bookId)
        }


        lateinit var book: Book
        lateinit var menuList: List<Menu>
        lateinit var seedList: List<Seed>
    }
}