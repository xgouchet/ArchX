package fr.xgouchet.archx.rx

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

interface SchedulerProvider {

    fun observeOn(): Scheduler

    fun subscribeOn(): Scheduler

}

fun <T> Maybe<T>.schedule(schedulerProvider: SchedulerProvider): Maybe<T> {
    return subscribeOn(schedulerProvider.subscribeOn())
            .observeOn(schedulerProvider.observeOn())
}

fun <T> Single<T>.schedule(schedulerProvider: SchedulerProvider): Single<T> {
    return subscribeOn(schedulerProvider.subscribeOn())
            .observeOn(schedulerProvider.observeOn())
}

fun <T> Flowable<T>.schedule(schedulerProvider: SchedulerProvider): Flowable<T> {
    return subscribeOn(schedulerProvider.subscribeOn())
            .observeOn(schedulerProvider.observeOn())
}
