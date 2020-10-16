package com.woowrale.openlibrary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.woowrale.openlibrary.di.components.DaggerFakeComponent
import com.woowrale.openlibrary.di.components.FakeComponent
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OpenLibraryMVVMTest {

    private lateinit var seeds: List<Seed>
    private lateinit var books: List<Book>

    private lateinit var component: FakeComponent
    private val disposable = CompositeDisposable()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        component = DaggerFakeComponent.builder().appTest(this).build()

        seeds = component.openLibraryRepository.searchSeed(BuildConfig.SEED_ID, BuildConfig.ENV_REMOTE)
        books = component.openLibraryRepository.searchBook(BuildConfig.BOOK_OLID_ID, BuildConfig.ENV_REMOTE)
    }

    @Test
    fun `Get Seeds view model`() {
        component.globalRemoteViewModel.getSeedList(
            disposable,
            BuildConfig.SEED_ID,
            BuildConfig.ENV_REMOTE
        ).observeForever {
            Assert.assertEquals(13, it.size)
        }
    }

    @Test
    fun `Save seed view model`() {
        component.globalRemoteViewModel.saveSeed(disposable, seeds[0]).observeForever {
            Assert.assertEquals(true, !it)
        }
    }

    @Test
    fun `Delete seed view model`() {
        component.globalLocalViewModel.deleteSeed(disposable, seeds[0]).observeForever {
            Assert.assertEquals(true, !it)
        }
    }

    @Test
    fun `Get book view model`() {
        component.detailsViewModel.getBooks(
            disposable,
            BuildConfig.BOOK_OLID_ID,
            BuildConfig.ENV_REMOTE
        ).observeForever {
            Assert.assertEquals(it[0].bibKey, BuildConfig.BOOK_OLID_ID)
        }
    }
}

