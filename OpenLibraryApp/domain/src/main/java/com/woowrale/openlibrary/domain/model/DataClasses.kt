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
    var picture: Picture,
    var lastUpdate: String,
    var title: String,
    var url: String,
    var ebookCount: Int,
    var editionCount: Int,
    var type: String,
    var fullUrl: String,
    var workCount: Int
)

data class Picture(
    var url: String
)

data class Book(
    var bibKeyId: String,
    var infoUrl: String,
    var bibKey: String,
    var thumbailUrl: String,
    var details: Details
)

data class Details(
    var title: String,
    var languages: List<Language>,
    var subTitle: String,
    var subjects: List<String>,
    var publishCountry: String,
    var byStatement: String,
    var authors: List<Author>,
    var numberOfPages: Int,
    var publishDate: String
)

data class Language(
    var key: String
)

data class Author(
    var name: String,
    var key: String
)