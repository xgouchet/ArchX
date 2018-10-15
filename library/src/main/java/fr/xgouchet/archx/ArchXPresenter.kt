package fr.xgouchet.archx

interface ArchXPresenter<VM> {
    fun onViewAttached(view: ArchXView<VM>, isRestored: Boolean)
    fun onViewDetached()
}