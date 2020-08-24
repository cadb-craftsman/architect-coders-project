package com.woowrale.openlibrary.data.remote.mappers

import com.woowrale.openlibrary.data.remote.model.response.*
import com.woowrale.openlibrary.domain.model.*
import com.woowrale.openlibrary.utils.DataUtils

fun MenuResponse.toMenu(): Menu = Menu(
    seedCount,
    editionCount,
    url,
    lastUpdate,
    fullUrl,
    name
)

fun SeedResponse.toSeed(): Seed = Seed(
    picture?.toPicture(),
    lastUpdate,
    title,
    DataUtils.getUrl(url),
    ebookCount,
    editionCount,
    type,
    fullUrl,
    workCount
)

fun PictureResponse.toPicture(): Picture = Picture(DataUtils.concatUrl(url))

fun BookResponse.toBook(): Book = Book(infoUrl, bibKey, thumbailUrl, details.toDetails())

fun DetailsResponse.toDetails(): Details = Details(
    title,
    languages?.map { it.toLanguage() },
    subTitle,
    subjects,
    publishCountry,
    byStatement,
    authors?.map { it.toAuthor() },
    numberOfPages,
    publishDate
)

fun AuthorResponse.toAuthor(): Author = Author(name, key)

fun LanguageResponse.toLanguage(): Language = Language(key)