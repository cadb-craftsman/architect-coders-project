package com.woowrale.openlibrary.data.local.mappers

import com.woowrale.openlibrary.data.local.model.BookEntity
import com.woowrale.openlibrary.data.local.model.DetailsEntity
import com.woowrale.openlibrary.data.local.model.SeedEntity
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Details
import com.woowrale.openlibrary.domain.model.Picture
import com.woowrale.openlibrary.domain.model.Seed

fun Seed.toSeedEntity(): SeedEntity = SeedEntity(
    id = 0,
    pictureUrl = picture?.url!!,
    lastUpdate = lastUpdate!!,
    title = title,
    url = url,
    ebookCount = ebookCount,
    editionCount = editionCount,
    type = type,
    fullUrl = fullUrl,
    workCount = workCount
)

fun SeedEntity.toSeed(): Seed = Seed(
    Picture(pictureUrl),
    lastUpdate,
    title,
    url,
    ebookCount,
    editionCount,
    type,
    fullUrl,
    workCount
)

fun Book.toBookEntity(): BookEntity = BookEntity(
    id = 0,
    bibKeyId = bibKey,
    infoUrl = infoUrl,
    bibKey = bibKey,
    thumbailUrl = thumbailUrl
)

fun BookEntity.toBook(): Book = Book(
    infoUrl = infoUrl,
    bibKey = bibKey,
    thumbailUrl = thumbailUrl,
    details = Details("", null, "", null, "", "", null, 0, "")
)

fun Details.toDetailsEntity(): DetailsEntity = DetailsEntity(
    id = 0,
    bibKeyId = "",
    title = title,
    subTitle = subTitle,
    publishCountry = publishCountry,
    byStatement = byStatement,
    numberOfPages = numberOfPages,
    publishDate = publishDate,
    subjects = subjects!!
)

