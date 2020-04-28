package com.woowrale.data.repository.local

import com.woowrale.domain.model.Contact

interface LocalContactSource {
    fun getContacts(source: String, query: String): List<Contact>
}

class LocalRepository : LocalContactSource {

    override fun getContacts(source: String, query: String): List<Contact> {
        TODO("Not yet implemented")
    }
}