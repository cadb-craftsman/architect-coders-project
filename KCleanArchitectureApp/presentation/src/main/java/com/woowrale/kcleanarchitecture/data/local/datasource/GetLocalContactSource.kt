package com.woowrale.kcleanarchitecture.data.local.datasource

import com.woowrale.data.repository.local.LocalContactSource
import com.woowrale.domain.model.Contact

class GetLocalContactSource: LocalContactSource{
    override fun getContacts(source: String, query: String): List<Contact> {
        TODO("Not yet implemented")
    }
}