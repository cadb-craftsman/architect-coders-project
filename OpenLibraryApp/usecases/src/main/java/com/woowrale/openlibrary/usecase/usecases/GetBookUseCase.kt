package com.woowrale.openlibrary.usecase.usecases

import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.domain.model.Book
import com.woowrale.openlibrary.usecase.base.BaseUseCase
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Single

class GetBookUseCase constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : BaseUseCase<List<Book>, GetSeedListUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: GetSeedListUseCase.Params): Single<List<Book>> {
        TODO("Not yet implemented")
    }

}