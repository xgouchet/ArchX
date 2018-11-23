package fr.xgouchet.archx.data

import io.reactivex.Flowable
import io.reactivex.Single


interface ArchXDataSource<D> {

    fun getData(): Single<D>

    fun listenData(): Flowable<D>

}
