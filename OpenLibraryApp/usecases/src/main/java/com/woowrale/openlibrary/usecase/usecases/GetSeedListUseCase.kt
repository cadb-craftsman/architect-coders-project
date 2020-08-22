package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.domain.model.Seed
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class GetSeedListUseCase(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : BaseUseCase<List<Seed>, GetSeedListUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<List<Seed>> {
        var single: Single<List<Seed>>? = null
        if(params.env.equals("REMOTE")){
            single =  Single.create { emitter: SingleEmitter<List<Seed>> ->
                try {
                    val seedList: List<Seed> = remoteRepository.searchSeedById(params.id)
                    emitter.onSuccess(seedList)
                } catch (exception: Exception) {
                    if (!emitter.isDisposed()) {
                        emitter.onError(exception)
                    }
                }
            }
        }else{
            single =  Single.create { emitter: SingleEmitter<List<Seed>> ->
                try {
                    val seedList: List<Seed> = localRepository.getAllSeeds()
                    emitter.onSuccess(seedList)
                } catch (exception: Exception) {
                    if (!emitter.isDisposed()) {
                        emitter.onError(exception)
                    }
                }
            }
        }

        return single!!
    }

    class Params(val id: String, val env: String)

}