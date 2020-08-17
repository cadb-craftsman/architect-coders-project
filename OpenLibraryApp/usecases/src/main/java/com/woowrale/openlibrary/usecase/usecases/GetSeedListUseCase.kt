package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class GetSeedListUseCase(
    private val remoteRepository: RemoteRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : BaseUseCase<List<Seed>, GetSeedListUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<List<Seed>> {
        return Single.create { emitter: SingleEmitter<List<Seed>> ->
            try {
                val seedList: List<Seed> = remoteRepository.searchSeedById(params.id)
                emitter.onSuccess(seedList)
            } catch (exception: Exception) {
                if (!emitter.isDisposed()) {
                    emitter.onError(exception)
                }
            }
        }
    }

    class Params(val id: String)

}