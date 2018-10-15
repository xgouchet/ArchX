package fr.xgouchet.archx

interface ArchXViewModelTransformer<AM, VM> {

    fun transform(appModel: AM): VM
}