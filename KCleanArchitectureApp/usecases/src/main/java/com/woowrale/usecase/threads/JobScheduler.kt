package com.woowrale.usecase.threads

import io.reactivex.Scheduler

interface JobScheduler {
    val scheduler: Scheduler
}
