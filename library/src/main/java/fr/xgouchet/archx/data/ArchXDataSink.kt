package fr.xgouchet.archx.data

import io.reactivex.Single

interface ArchXDataSink<D> {

    fun createData(data: D): Single<D>

    fun updateData(data: D): Single<D>

    fun deleteData(data: D): Single<D>
}
