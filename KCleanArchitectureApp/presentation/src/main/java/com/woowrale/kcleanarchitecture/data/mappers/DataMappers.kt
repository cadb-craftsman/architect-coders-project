package com.woowrale.kcleanarchitecture.data.mappers

import com.woowrale.domain.model.Contact
import com.woowrale.kcleanarchitecture.data.remote.model.response.ContactResponse

class DataMappers {
    companion object {
        fun mapToContact(contactsResponse: List<ContactResponse>?): List<Contact> {
            val contacts: MutableList<Contact> = ArrayList<Contact>()
            for (cr in contactsResponse!!) {
                contacts.add(Contact(cr.name, cr.profileImage, cr.phone, cr.email))
            }

            return contacts
        }
    }


}