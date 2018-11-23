package fr.xgouchet.archx.data

import io.reactivex.Single

open class ArchXNoOpDataSink<D>
    : ArchXDataSink<D> {

    override fun createData(data: D): Single<D> {
        return Single.just(data)
    }

    override fun updateData(data: D): Single<D> {
        return Single.just(data)
    }

    override fun deleteData(data: D): Single<D> {
        return Single.just(data)
    }

}
