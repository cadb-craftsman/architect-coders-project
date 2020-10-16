package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class SaveBookUseCase(
    private val openLibraryRepository: OpenLibraryRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : BaseUseCase<Boolean, SaveBookUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<Boolean> {
        var single: Single<Boolean>? = null
        single = Single.create { emitter: SingleEmitter<Boolean> ->
            try {
                emitter.onSuccess(openLibraryRepository.saveBook(params.book))
            } catch (exception: Exception) {
                if (!emitter.isDisposed()) {
                    emitter.onError(exception)
                }
            }
        }

        return single
    }

    class Params(val book: Book)

}