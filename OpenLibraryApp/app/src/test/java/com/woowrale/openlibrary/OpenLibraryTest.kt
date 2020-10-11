package com.woowrale.openlibrary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.woowrale.openlibrary.di.components.DaggerFakeComponent
import com.woowrale.openlibrary.di.components.FakeComponent

import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.ui.observers.BooksObserver
import com.woowrale.openlibrary.ui.observers.DeleteSeedObserver
import com.woowrale.openlibrary.ui.observers.GetSeedsObserver
import com.woowrale.openlibrary.ui.observers.SaveSeedObserver
import com.woowrale.openlibrary.usecase.usecases.DeleteSeedUseCase
import com.woowrale.openlibrary.usecase.usecases.GetDetailBookUseCase
import com.woowrale.openlibrary.usecase.usecases.GetSeedListUseCase
import com.woowrale.openlibrary.usecase.usecases.SaveSeedUseCase
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OpenLibraryTest {

    private val SEED_ID = "OL25637743M"

    private lateinit var seeds: List<Seed>
    private lateinit var books: List<Book>

    private lateinit var component: FakeComponent
    private val disposable = CompositeDisposable()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        component = DaggerFakeComponent.builder().appTest(this).build()

        seeds = component.remoteRepository.searchSeedById(BuildConfig.SEED_ID)
        books = component.remoteRepository.searhBookByOLID(BuildConfig.BOOK_OLID_ID)
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
        Assert.assertEquals(true, component.localRepository.saveSeed(seeds[0]))
    }

    @Test
    fun `Search seed in local`() {
        seeds.forEach {
            component.localRepository.saveSeed(it)
        }
        Assert.assertEquals(
            SEED_ID,
            component.localRepository.searchSeedById(SEED_ID)[0].olid
        )
    }

    @Test
    fun `Remove all local seeds`() {
        val seeds = component.localRepository.getAllSeeds()
        seeds.forEach {
            component.localRepository.deleteSeed(it)
        }
        Assert.assertEquals(0, seeds.size)
    }

    @Test
    fun `Save book in local`() {
        Assert.assertEquals(true, component.localRepository.saveBook(books[0]))
    }

    @Test
    fun `Search book in local`() {
        component.localRepository.saveBook(books[0])
        Assert.assertEquals(
            "Hoover's book of recipes",
            component.localRepository.searhBookByOLID(BuildConfig.BOOK_OLID_ID)[0].details!!.title
        )
    }

    @Test
    fun `Get all seeds use case`() {
        val seeds = MutableLiveData<List<Seed>>()
        disposable.add(
            component.useCaseFactory.getSeedListUseCase().execute(
                GetSeedsObserver(seeds),
                GetSeedListUseCase.Params(BuildConfig.SEED_ID, BuildConfig.ENV_REMOTE)
            )
        )
        seeds.observeForever{
            Assert.assertEquals(13, it.size)
        }
    }

    @Test
    fun `Save seed use case`(){
        val isSaved = MutableLiveData<Boolean>()
        disposable.add(
            component.useCaseFactory.saveSeedUseCase()
                .execute(SaveSeedObserver(isSaved), SaveSeedUseCase.Params(seeds[0]))
        )

        isSaved.observeForever{
            Assert.assertEquals(true, it)
        }
    }

    @Test
    fun `Delete seed use case`(){
        val isSaved = MutableLiveData<Boolean>()
        disposable.add(
            component.useCaseFactory.deleteSeedUseCase()
                .execute(DeleteSeedObserver(isSaved), DeleteSeedUseCase.Params(seeds[0]))
        )
        isSaved.observeForever{
            Assert.assertEquals(true, it)
        }
    }

    @Test
    fun `Get Book use case`(){
        val books: MutableLiveData<List<Book>> = MutableLiveData()
        disposable.add(
            component.useCaseFactory.getDetailBookUseCase().execute(
                BooksObserver(books),
                GetDetailBookUseCase.Params(BuildConfig.BOOK_OLID_ID, BuildConfig.ENV_REMOTE)
            )
        )
        books.observeForever{
            Assert.assertEquals("Hoover's book of recipes", it[0].details!!.title)
        }
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

