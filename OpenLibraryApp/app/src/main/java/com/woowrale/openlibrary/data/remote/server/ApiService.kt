package com.woowrale.openlibrary.data.remote.server

import com.woowrale.openlibrary.data.remote.model.response.BookEntriesResponse
import com.woowrale.openlibrary.data.remote.model.response.BookResponse
import com.woowrale.openlibrary.data.remote.model.response.SeedEntriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    /*
    @GET
    fun getMenuList(
        @Url apiContacts: String,
        @Query("source") source: String,
        @Query("query") query: String? = null
    ): Call<List<MenuResponse>>
     */

    @GET
    fun getSeedList(@Url seedId: String): Call<SeedEntriesResponse>

    @GET
    fun getBookList(@Url bookOlid: String): Call<Map<String, BookResponse>>
}
