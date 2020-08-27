package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class GetDetailBookUseCase(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
): BaseUseCase<List<Book>, GetDetailBookUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<List<Book>> {
        var single: Single<List<Book>>? = null
        if (params.env.equals("REMOTE")) {
            single = Single.create { emitter: SingleEmitter<List<Book>> ->
                try {
                    val bookList: List<Book> = remoteRepository.searhBookByOLID(params.olid)
                    emitter.onSuccess(bookList)
                } catch (exception: Exception) {
                    if (!emitter.isDisposed()) {
                        emitter.onError(exception)
                    }
                }
            }
        } else {
            single = Single.create { emitter: SingleEmitter<List<Book>> ->
                try {
                    var bookList: List<Book> = localRepository.searhBookByOLID(params.olid)
                    if(bookList.isNullOrEmpty()){
                        bookList = remoteRepository.searhBookByOLID(params.olid)
                        localRepository.saveBook(bookList[0])
                    }
                    emitter.onSuccess(bookList)
                } catch (exception: Exception) {
                    if (!emitter.isDisposed()) {
                        emitter.onError(exception)
                    }
                }
            }
        }
        return single
    }

    class Params(val olid: String, val env: String)
}