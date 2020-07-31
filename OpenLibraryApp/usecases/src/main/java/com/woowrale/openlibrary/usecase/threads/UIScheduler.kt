package com.woowrale.openlibrary.usecase.threads

import io.reactivex.Scheduler

interface UIScheduler {
    val scheduler: Scheduler
}