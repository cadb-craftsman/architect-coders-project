package com.woowrale.openlibrary.di.factory

import com.woowrale.openlibrary.data.repository.OpenLibraryRepository
import com.woowrale.openlibrary.usecase.threads.JobScheduler
import com.woowrale.openlibrary.usecase.threads.UIScheduler
import com.woowrale.openlibrary.usecase.usecases.*
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val openLibraryRepository: OpenLibraryRepository,
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) {

    fun getSeedListUseCase(): GetSeedListUseCase {
        return GetSeedListUseCase(openLibraryRepository, uiScheduler, jobScheduler)
    }

    fun saveSeedUseCase(): SaveSeedUseCase{
        return SaveSeedUseCase(openLibraryRepository, uiScheduler, jobScheduler)
    }

    fun getDetailBookUseCase():GetDetailBookUseCase{
        return GetDetailBookUseCase(openLibraryRepository, uiScheduler, jobScheduler)
    }

    fun deleteSeedUseCase(): DeleteSeedUseCase{
        return DeleteSeedUseCase(openLibraryRepository, uiScheduler, jobScheduler)
    }

    fun saveBookUseCase(): SaveBookUseCase{
        return SaveBookUseCase(openLibraryRepository, uiScheduler, jobScheduler)
    }

}