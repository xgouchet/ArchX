package fr.xgouchet.archx

interface ArchXView<VM> {
    fun bindPresenter(presenter: ArchXPresenter<VM>)
    fun showData(viewModel: VM)
}