package fr.xgouchet.archx.data

interface ArchXDataSink<D> {

    fun createData(data: D, onEnd: (Throwable?) -> Unit)

    fun updateData(data: D, onEnd: (Throwable?) -> Unit)

    fun deleteData(data: D, onEnd: (Throwable?) -> Unit)
}
