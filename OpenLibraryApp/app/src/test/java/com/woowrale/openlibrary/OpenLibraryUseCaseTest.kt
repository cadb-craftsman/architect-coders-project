package com.woowrale.openlibrary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.woowrale.openlibrary.di.components.DaggerFakeUseCaseComponent
import com.woowrale.openlibrary.di.components.FakeUseCaseComponent
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
class OpenLibraryUseCaseTest {

    private lateinit var seeds: List<Seed>
    private lateinit var books: List<Book>

    private lateinit var component: FakeUseCaseComponent
    private val disposable = CompositeDisposable()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        component = DaggerFakeUseCaseComponent.builder().appTest(this).build()

        seeds =
            component.openLibraryRepository.searchSeed(BuildConfig.SEED_ID, BuildConfig.ENV_REMOTE)
        books = component.openLibraryRepository.searchBook(
            BuildConfig.BOOK_OLID_ID,
            BuildConfig.ENV_REMOTE
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
        seeds.observeForever {
            Assert.assertEquals(13, it.size)
        }
    }

    @Test
    fun `Save seed use case`() {
        val isSaved = MutableLiveData<Boolean>()
        disposable.add(
            component.useCaseFactory.saveSeedUseCase()
                .execute(SaveSeedObserver(isSaved), SaveSeedUseCase.Params(seeds[0]))
        )

        isSaved.observeForever {
            Assert.assertEquals(true, it)
        }
    }

    @Test
    fun `Delete seed use case`() {
        val isSaved = MutableLiveData<Boolean>()
        disposable.add(
            component.useCaseFactory.deleteSeedUseCase()
                .execute(DeleteSeedObserver(isSaved), DeleteSeedUseCase.Params(seeds[0]))
        )
        isSaved.observeForever {
            Assert.assertEquals(true, it)
        }
    }

    @Test
    fun `Get Book use case`() {
        val books: MutableLiveData<List<Book>> = MutableLiveData()
        disposable.add(
            component.useCaseFactory.getDetailBookUseCase().execute(
                BooksObserver(books),
                GetDetailBookUseCase.Params(BuildConfig.BOOK_OLID_ID, BuildConfig.ENV_REMOTE)
            )
        )
        books.observeForever {
            Assert.assertEquals("Hoover's book of recipes", it[0].details!!.title)
        }
    }
}