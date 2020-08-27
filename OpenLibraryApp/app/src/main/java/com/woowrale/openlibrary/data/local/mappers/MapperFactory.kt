package com.woowrale.openlibrary.data.local.mappers

import com.woowrale.openlibrary.data.local.model.BookEntity
import com.woowrale.openlibrary.data.local.model.DetailsEntity
import com.woowrale.openlibrary.data.local.model.SeedEntity
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Details
import com.woowrale.openlibrary.domain.model.Picture
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.utils.DataUtils

fun Seed.toSeedEntity(): SeedEntity = SeedEntity(
    id = 0,
    pictureUrl = picture?.url!!,
    lastUpdate = lastUpdate!!,
    title = title,
    olid = olid,
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
    olid,
    ebookCount,
    editionCount,
    type,
    fullUrl,
    workCount
)

fun Book.toBookEntity(): BookEntity = BookEntity(
    id = 0,
    bibKey = DataUtils.getBibKey(bibKey),
    infoUrl = infoUrl,
    thumbailUrl = thumbailUrl,
    title = details?.title!!,
    subTitle = details?.subTitle!!,
    language = details?.languages.toString(),
    authors = details?.authors.toString()
)

fun BookEntity.toBook(): Book = Book(
    infoUrl = infoUrl!!,
    bibKey = bibKey!!,
    thumbailUrl = thumbailUrl!!,
    details = Details(title!!, null, subTitle, null, "", "", null, 0, "")
)

fun Details.toDetailsEntity(): DetailsEntity = DetailsEntity(
    id = 0,
    bibKey = "",
    title = title,
    subTitle = subTitle!!,
    publishCountry = publishCountry!!,
    byStatement = byStatement!!,
    numberOfPages = numberOfPages!!,
    publishDate = publishDate!!,
    subjects = subjects!!
)

