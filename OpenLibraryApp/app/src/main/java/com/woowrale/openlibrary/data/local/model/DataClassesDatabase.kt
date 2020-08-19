package com.woowrale.openlibrary.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var bibKeyId: String,
    var infoUrl: String,
    var bibKey: String,
    var thumbailUrl: String
)

@Entity(tableName = "details")
data class Details(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var bibKeyId: String,
    var title: String,
    //@Ignore var languages: List<Language>,
    var subTitle: String,
    var subjects: List<String>,
    var publishCountry: String,
    var byStatement: String,
    //@Ignore var authors: List<Author>,
    var numberOfPages: Int,
    var publishDate: String
)

@Entity(tableName = "language")
data class Language(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var detailsId: Int,
    var key: String
)

@Entity(tableName = "authors")
data class Author(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var detailsId: Int,
    var name: String,
    var key: String
)