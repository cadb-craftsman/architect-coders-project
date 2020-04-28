package com.woowrale.kcleanarchitecture.data.remote.ws

import com.woowrale.kcleanarchitecture.data.remote.model.response.ContactResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getContacts(
        @Url apiContacts: String,
        @Query("source") source: String,
        @Query("query") query: String? = null
    ): Call<List<ContactResponse>>
}
