package fr.xgouchet.archx

open class ArchXViewModelListTransformer<AM, VM>(
        protected val delegate: ArchXViewModelTransformer<AM, VM>
) : ArchXViewModelTransformer<List<AM>, List<VM>> {

    override fun transform(appModel: List<AM>): List<VM> {
        return appModel.map { delegate.transform(it) }
    }

}
