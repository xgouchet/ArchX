package fr.xgouchet.archx.data

open class ArchXNoOpDataSink<D>
    : ArchXDataSink<D> {

    override fun createData(data: D, onEnd: (Throwable?) -> Unit) {
    }

    override fun updateData(data: D, onEnd: (Throwable?) -> Unit) {
    }

    override fun deleteData(data: D, onEnd: (Throwable?) -> Unit) {
    }

}
