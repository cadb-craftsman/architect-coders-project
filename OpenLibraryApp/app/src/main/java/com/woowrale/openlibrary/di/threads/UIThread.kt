package com.woowrale.openlibrary.di.threads

import com.woowrale.openlibrary.usecase.threads.UIScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UIThread @Inject internal constructor() : UIScheduler {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}