package com.woowrale.openlibrary.usecase.threads

import io.reactivex.Scheduler

interface JobScheduler {
    val scheduler: Scheduler
}
