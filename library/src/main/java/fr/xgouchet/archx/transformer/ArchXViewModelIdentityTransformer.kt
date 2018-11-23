package fr.xgouchet.archx.transformer

class ArchXViewModelIdentityTransformer<T>
    : ArchXViewModelTransformer<T, T> {

    override fun transform(appModel: T): T {
        return appModel
    }
}
