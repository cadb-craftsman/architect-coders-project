package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class GetSeedListUseCase(
    private val openLibraryRepository: OpenLibraryRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : BaseUseCase<List<Seed>, GetSeedListUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<List<Seed>> {
        var single: Single<List<Seed>>? = null
        single = Single.create { emitter: SingleEmitter<List<Seed>> ->
            try {
                emitter.onSuccess(openLibraryRepository.searchSeed(params.id, params.env))
            } catch (exception: Exception) {
                if (!emitter.isDisposed()) {
                    emitter.onError(exception)
                }
            }
        }
        return single
    }

    class Params(val id: String, val env: String)

}