package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class GetDetailBookUseCase(
    private val openLibraryRepository: OpenLibraryRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
): BaseUseCase<List<Book>, GetDetailBookUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<List<Book>> {
        var single: Single<List<Book>>? = null

        single = Single.create { emitter: SingleEmitter<List<Book>> ->
            try {
                emitter.onSuccess(openLibraryRepository.searchBook(params.olid, params.env))
            } catch (exception: Exception) {
                if (!emitter.isDisposed()) {
                    emitter.onError(exception)
                }
            }
        }

        return single
    }

    class Params(val olid: String, val env: String)
}