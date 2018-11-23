package fr.xgouchet.archx.data

import io.reactivex.Observable
import io.reactivex.Single


interface ArchXDataSource<D> {

    fun getData(): Single<D>

    fun listenData(): Observable<D>

}
