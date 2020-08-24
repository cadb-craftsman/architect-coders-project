package com.woowrale.openlibrary.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class MenuEntriesResponse(
    @SerializedName("entries")
    var entries: List<MenuResponse>
)

data class MenuResponse(
    @SerializedName("seed_count")
    var seedCount: Int,
    @SerializedName("edition_count")
    var editionCount: Int,
    @SerializedName("url")
    var url: String,
    @SerializedName("last_update")
    var lastUpdate: String,
    @SerializedName("full_url")
    var fullUrl: String,
    @SerializedName("name")
    var name: String
)

data class SeedEntriesResponse(
    @SerializedName("entries")
    var entries: List<SeedResponse>
)

data class SeedResponse(
    @SerializedName("picture")
    var picture: PictureResponse,
    @SerializedName("last_update")
    var lastUpdate: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("ebook_count")
    var ebookCount: Int,
    @SerializedName("edition_count")
    var editionCount: Int,
    @SerializedName("type")
    var type: String,
    @SerializedName("full_url")
    var fullUrl: String,
    @SerializedName("work_count")
    var workCount: Int
)

data class PictureResponse(
    @SerializedName("url")
    var url: String
)

data class BookEntriesResponse(
    var book: Map<String, BookResponse>
)

data class BookResponse(
    @SerializedName("info_url")
    var infoUrl: String,
    @SerializedName("bib_key")
    var bibKey: String,
    @SerializedName("thumbnail_url")
    var thumbailUrl: String,
    @SerializedName("details")
    var details: DetailsResponse
)

data class DetailsResponse(
    @SerializedName("title")
    var title: String,
    @SerializedName("languages")
    var languages: List<LanguageResponse>? = null,
    @SerializedName("subtitle")
    var subTitle: String? = null,
    @SerializedName("subjects")
    var subjects: List<String>? = null,
    @SerializedName("publish_country")
    var publishCountry: String? = null,
    @SerializedName("by_statement")
    var byStatement: String? = null,
    @SerializedName("authors")
    var authors: List<AuthorResponse>? = null,
    @SerializedName("number_of_pages")
    var numberOfPages: Int? = null,
    @SerializedName("publish_date")
    var publishDate: String? = null
)

data class LanguageResponse(
    @SerializedName("key")
    var key: String
)

data class AuthorResponse(
    @SerializedName("name")
    var name: String,
    @SerializedName("key")
    var key: String
)