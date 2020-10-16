package com.woowrale.openlibrary

import com.woowrale.openlibrary.di.components.DaggerFakeRepositoryComponent
import com.woowrale.openlibrary.di.components.FakeRepositoryComponent
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OpenLibraryRepositoryTest {

    private val SEED_ID = "OL25637743M"

    private lateinit var seeds: List<Seed>
    private lateinit var books: List<Book>

    private lateinit var component: FakeRepositoryComponent

    @Before
    fun setUp() {
        component = DaggerFakeRepositoryComponent.builder().appTest(this).build()

        seeds = component.openLibraryRepository.searchSeed(BuildConfig.SEED_ID, BuildConfig.ENV_REMOTE)
        books = component.openLibraryRepository.searchBook(BuildConfig.BOOK_OLID_ID, BuildConfig.ENV_REMOTE)
    }

    @Test
    fun `Search seed in remote`() {
        Assert.assertEquals(13, seeds.size)
    }

    @Test
    fun `Search book in remote`() {
        Assert.assertEquals(BuildConfig.BOOK_OLID_ID, books[0].bibKey)
    }

    @Test
    fun `Save seed in local`() {
        Assert.assertEquals(true, component.openLibraryRepository.saveSeed(seeds[0]))
    }

    @Test
    fun `Search seed in local`() {
        seeds.forEach {
            component.openLibraryRepository.saveSeed(it)
        }
        Assert.assertEquals(
            SEED_ID,
            component.openLibraryRepository.searchSeed(SEED_ID, BuildConfig.ENV_LOCAL)[0].olid
        )
    }

    @Test
    fun `Remove all local seeds`() {
        val seeds = component.openLibraryRepository.searchSeed(SEED_ID, BuildConfig.ENV_LOCAL)
        seeds.forEach {
            component.openLibraryRepository.deleteSeed(it)
        }
        Assert.assertEquals(0, seeds.size)
    }

    @Test
    fun `Save book in local`() {
        Assert.assertEquals(true, component.openLibraryRepository.saveBook(books[0]))
    }

    @Test
    fun `Search book in local`() {
        component.openLibraryRepository.saveBook(books[0])
        Assert.assertEquals(
            "Hoover's book of recipes",
            component.openLibraryRepository.searchBook(BuildConfig.BOOK_OLID_ID, BuildConfig.ENV_LOCAL)[0].details!!.title
        )
    }

}