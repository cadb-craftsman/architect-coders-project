package com.woowrale.usecase.base

import com.woowrale.usecase.threads.JobScheduler
import com.woowrale.usecase.threads.UIScheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

abstract class BaseUseCase<Observer, Params> constructor(
    uiScheduler: UIScheduler,
    jobScheduler: JobScheduler
) {
    private val uiScheduler: UIScheduler = uiScheduler
    private val jobScheduler: JobScheduler = jobScheduler

    protected abstract fun buildUseCaseObservable(params: Params): Single<Observer>

    fun execute(observer: DisposableSingleObserver<Observer>, params: Params): Disposable {
        val observable = buildUseCaseObservable(params)
            .observeOn(uiScheduler.scheduler)
            .subscribeOn(jobScheduler.scheduler)
        return observable.subscribeWith(observer)
    }
}