package fr.xgouchet.archx.data

interface ArchXDataSink<D> {

    fun createData(data: D)

    fun updateData(data: D)

    fun deleteData(data: D)
}
