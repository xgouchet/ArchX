package fr.xgouchet.archx.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import fr.xgouchet.archx.ArchXPresenter
import fr.xgouchet.archx.ArchXView
import fr.xgouchet.archx.ArchXViewModelTransformer

abstract class ArchXDataPresenter<D, V, VM>(
        private val owner: LifecycleOwner,
        protected val dataSource: ArchXDataSource<D>,
        protected val transformer: ArchXViewModelTransformer<D, VM>
) : ArchXPresenter<VM>,
        Observer<D>
        where V : ArchXView<VM> {

    protected var view: V? = null

    // region ArchXContract.Presenter

    override fun onViewAttached(view: ArchXView<VM>, isRestored: Boolean) {
        this.view = view as? V
        this.view?.bindPresenter(this)

        dataSource.getData().observe(owner, this)
    }

    override fun onViewDetached() {
        view = null

        dataSource.getData().removeObserver(this)
    }

    // endregion

    // region Observer

    override fun onChanged(t: D) {
        val viewModel = transformer.transform(t)
        view?.showData(viewModel)
    }

}