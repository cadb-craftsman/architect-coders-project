package com.woowrale.openlibrary

import com.woowrale.openlibrary.di.components.DaggerFakeComponent
import com.woowrale.openlibrary.di.components.FakeComponent
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OpenLibraryTest {

    private lateinit var component: FakeComponent

    @Before
    fun setUp() {
        component = DaggerFakeComponent.builder().appTest(this).build()
    }

    @Test
    fun `Search seed in remote`() {
        val seeds = component.remoteRepository.searchSeedById("OL97L")
        Assert.assertEquals(5, seeds.size)
    }

    @Test
    fun `Search book in remote`() {
        val book = component.remoteRepository.searhBookByOLID("OL23662890M")
        Assert.assertEquals("OL23662890M", book.get(0).bibKey)
    }

    @Test
    fun `Save seed in local`() {
        val seeds = component.remoteRepository.searchSeedById("OL97L")
        Assert.assertEquals(true, component.localRepository.saveSeed(seeds.get(0)))
    }

    @Test
    fun `Search seed in local`(){
        val seeds = component.remoteRepository.searchSeedById("OL97L")
        component.localRepository.saveSeed(seeds.get(0))
        val seedss = component.localRepository.searchSeedById("OL24331810M")
        Assert.assertEquals("OL24331810M", seedss.get(0).olid)
    }


    @Test
    fun `Remove all local seeds`() {
        val seeds = component.localRepository.getAllSeeds()
        seeds.forEach({
            component.localRepository.deleteSeed(it)
        })
        Assert.assertEquals(0, seeds.size)
    }

    @Test
    fun `Save book in local`(){
        val books = component.remoteRepository.searhBookByOLID("OL23662890M")
        Assert.assertEquals(true, component.localRepository.saveBook(books.get(0)))
    }

    @Test
    fun `Search book in local`(){
        val books = component.remoteRepository.searhBookByOLID("OL23662890M")
        component.localRepository.saveBook(books.get(0))
        val book = component.localRepository.searhBookByOLID("OL23662890M")
        Assert.assertEquals("Hoover's book of recipes", book.get(0).details!!.title)
    }

}

