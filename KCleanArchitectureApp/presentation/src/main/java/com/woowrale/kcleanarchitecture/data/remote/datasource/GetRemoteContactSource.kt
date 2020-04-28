package com.woowrale.kcleanarchitecture.data.remote.datasource

import com.woowrale.data.repository.remote.RemoteContactSource
import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.BuildConfig
import com.woowrale.kcleanarchitecture.data.mappers.DataMappers
import com.woowrale.kcleanarchitecture.data.remote.ws.ApiService
import java.io.IOException

class GetRemoteContactSource(apiService: ApiService) : RemoteContactSource {

    private val apiService: ApiService = apiService

    override fun getContacts(
        source: String,
        query: String
    ): List<Contact> {
        return try {
            DataMappers.mapToContact(apiService.getContacts(BuildConfig.GET_CONTACTS, source).execute().body())
        } catch (e: IOException) {
           return listOfNotNull()
        }
    }

}

