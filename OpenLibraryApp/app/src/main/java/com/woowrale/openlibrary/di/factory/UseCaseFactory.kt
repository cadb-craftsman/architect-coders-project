package com.woowrale.openlibrary.di.factory

import com.woowrale.openlibrary.data.local.LocalRepository
import com.woowrale.openlibrary.data.remote.RemoteRepository
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import com.woowrale.openlibrary.usecase.usecases.GetDetailBookUseCase
import com.woowrale.openlibrary.usecase.usecases.GetSeedListUseCase
import com.woowrale.openlibrary.usecase.usecases.SaveSeedUseCase
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) {

    fun getSeedListUseCase(): GetSeedListUseCase {
        return GetSeedListUseCase(remoteRepository, localRepository, uiScheduler, jobScheduler)
    }

    fun saveSeedUseCase(): SaveSeedUseCase{
        return SaveSeedUseCase(localRepository, uiScheduler, jobScheduler)
    }

    fun getDetailBookUseCase():GetDetailBookUseCase{
        return GetDetailBookUseCase(remoteRepository, localRepository, uiScheduler, jobScheduler)
    }

}