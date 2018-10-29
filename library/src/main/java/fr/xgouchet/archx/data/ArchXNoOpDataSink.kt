package fr.xgouchet.archx.data

open class ArchXNoOpDataSink<D>
    : ArchXDataSink<D> {
    override fun createData(data: D) {
    }

    override fun updateData(data: D) {
    }

    override fun deleteData(data: D) {
    }
}
