package fr.xgouchet.archx.transformer

open class ArchXViewModelListTransformer<AM, VM>(
        protected val delegate: ArchXViewModelTransformer<AM, VM>
) : ArchXViewModelTransformer<List<AM>, List<VM>> {

    override fun transform(appModel: List<AM>): List<VM> {
        return appModel.map { delegate.transform(it) }
    }

}

fun <AM, VM> ArchXViewModelTransformer<AM, VM>.toListTransformer(): ArchXViewModelTransformer<List<AM>, List<VM>> {
    return ArchXViewModelListTransformer(this)
}
