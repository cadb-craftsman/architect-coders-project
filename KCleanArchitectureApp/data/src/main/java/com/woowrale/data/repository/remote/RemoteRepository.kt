package com.woowrale.data.repository.remote

import com.woowrale.domain.model.Contact

interface RemoteContactSource {
    fun getContacts(source: String, query: String): List<Contact>
}

class RemoteRepository(private val remoteContactSource: RemoteContactSource) {
    fun getContacts(source: String, query: String): List<Contact> {
        return remoteContactSource.getContacts(source, query)
    }
}
