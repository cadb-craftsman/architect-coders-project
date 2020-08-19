package com.woowrale.openlibrary.di.factory

import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import com.woowrale.openlibrary.usecase.usecases.GetSeedListUseCase
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) {

    fun getSeedListUseCase(): GetSeedListUseCase{
        return GetSeedListUseCase(remoteRepository, uiScheduler, jobScheduler)
    }

}