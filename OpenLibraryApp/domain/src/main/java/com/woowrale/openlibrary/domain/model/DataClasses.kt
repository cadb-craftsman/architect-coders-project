package com.woowrale.openlibrary.domain.model

data class Menu(
    var seedCount: Int,
    var editionCount: Int,
    var url: String,
    var lastUpdate: String,
    var fullUrl: String,
    var name: String
)

data class Seed(
    var picture: Picture? = null,
    var lastUpdate: String? = "",
    var title: String,
    var olid: String,
    var ebookCount: Int,
    var editionCount: Int,
    var type: String,
    var fullUrl: String,
    var workCount: Int
)

data class Picture(
    var url: String? = ""
)

data class Book(
    var infoUrl: String,
    var bibKey: String,
    var thumbailUrl: String,
    var details: Details? = null
)

data class Details(
    var title: String,
    var languages: List<Language>? = null,
    var subTitle: String? = null,
    var subjects: List<String>? = null,
    var publishCountry: String? = null,
    var byStatement: String? = null,
    var authors: List<Author>? = null,
    var numberOfPages: Int? = null,
    var publishDate: String? = null
)

data class Language(
    var key: String
)

data class Author(
    var name: String,
    var key: String
)