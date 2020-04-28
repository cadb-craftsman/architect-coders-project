package com.woowrale.usecase.usecases

import com.woowrale.data.repository.remote.RemoteRepository
import com.woowrale.domain.model.Contact
import com.woowrale.usecase.base.BaseUseCase
import com.woowrale.usecase.threads.JobScheduler
import com.woowrale.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter

class GetContactsUseCase(
    private val remoteRepository: RemoteRepository,
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) : BaseUseCase<List<Contact>, GetContactsUseCase.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<List<Contact>> {
        return Single.create { emitter: SingleEmitter<List<Contact>> ->
            try {
                val contacts: List<Contact> = remoteRepository.getContacts(params.source, params.query)
                emitter.onSuccess(contacts)
            } catch (exception: Exception) {
                if (!emitter.isDisposed()) {
                    emitter.onError(exception)
                }
            }
        }
    }

    class Params(
        val source: String,
        val query: String
    )
}