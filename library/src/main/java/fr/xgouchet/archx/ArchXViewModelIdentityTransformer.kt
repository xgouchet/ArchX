package fr.xgouchet.archx

class ArchXViewModelIdentityTransformer<T>
    : ArchXViewModelTransformer<T, T> {

    override fun transform(appModel: T): T {
        return appModel
    }
}
