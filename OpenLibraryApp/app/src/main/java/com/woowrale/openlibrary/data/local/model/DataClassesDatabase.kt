package com.woowrale.openlibrary.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seeds")
data class SeedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var pictureUrl: String,
    var lastUpdate: String,
    var title: String,
    var olid: String,
    var ebookCount: Int,
    var editionCount: Int,
    var type: String,
    var fullUrl: String,
    var workCount: Int
)

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var bibKey: String,
    var infoUrl: String,
    var thumbailUrl: String
)

@Entity(tableName = "details")
data class DetailsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var bibKey: String,
    var title: String,
    var subTitle: String,
    var subjects: List<String>,
    var publishCountry: String,
    var byStatement: String,
    var numberOfPages: Int,
    var publishDate: String
)

@Entity(tableName = "language")
data class LanguageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var detailsId: Int,
    var key: String
)

@Entity(tableName = "authors")
data class AuthorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var detailsId: Int,
    var name: String,
    var key: String
)