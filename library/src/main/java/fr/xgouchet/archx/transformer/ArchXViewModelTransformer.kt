package fr.xgouchet.archx.transformer

interface ArchXViewModelTransformer<AM, VM> {

    fun transform(appModel: AM): VM
}
