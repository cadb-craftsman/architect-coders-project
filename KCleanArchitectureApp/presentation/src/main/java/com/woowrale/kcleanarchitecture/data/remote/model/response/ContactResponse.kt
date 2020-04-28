package com.woowrale.kcleanarchitecture.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ContactResponse(
    @SerializedName("name")
    var name: String,
    @SerializedName("image")
    var profileImage: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("email")
    var email: String
)
