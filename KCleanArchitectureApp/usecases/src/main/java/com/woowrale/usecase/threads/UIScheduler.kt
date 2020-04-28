package com.woowrale.usecase.threads

import io.reactivex.Scheduler

interface UIScheduler {
    val scheduler: Scheduler
}