package com.woowrale.kcleanarchitecture.di.factory

import com.woowrale.data.repository.remote.RemoteRepository
import com.woowrale.usecase.threads.JobScheduler
import com.woowrale.usecase.threads.UIScheduler
import com.woowrale.usecase.usecases.GetContactsUseCase
import javax.inject.Inject

class UseCaseFactory @Inject constructor(
    private val repository: RemoteRepository,
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) {

    fun getContacts(): GetContactsUseCase {
       return GetContactsUseCase(repository, uiScheduler, jobScheduler)
    }
}