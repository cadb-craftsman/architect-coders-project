package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class DeleteSeedUseCase(
    private val openLibraryRepository: OpenLibraryRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : BaseUseCase<Boolean, DeleteSeedUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<Boolean> {
        var single: Single<Boolean>? = null
        single = Single.create { emitter: SingleEmitter<Boolean> ->
            try {
                emitter.onSuccess(openLibraryRepository.deleteSeed(params.seed))
            } catch (exception: Exception) {
                if (!emitter.isDisposed()) {
                    emitter.onError(exception)
                }
            }
        }

        return single
    }

    class Params(val seed: Seed)

}