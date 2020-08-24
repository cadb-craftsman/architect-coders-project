package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class DeleteSeedUseCase(
    private val localRepository: LocalRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : BaseUseCase<Unit, DeleteSeedUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<Unit> {
        var single: Single<Unit>? = null
        single = Single.create { emitter: SingleEmitter<Unit> ->
            try {
                val seed: Unit = localRepository.deleteSeed(params.seed)
                emitter.onSuccess(seed)
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