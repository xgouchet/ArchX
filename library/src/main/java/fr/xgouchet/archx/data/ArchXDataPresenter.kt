package fr.xgouchet.archx.data

import fr.xgouchet.archx.ArchXPresenter
import fr.xgouchet.archx.ArchXView
import fr.xgouchet.archx.rx.SchedulerProvider
import fr.xgouchet.archx.rx.schedule
import fr.xgouchet.archx.transformer.ArchXViewModelTransformer
import io.reactivex.disposables.Disposable

abstract class ArchXDataPresenter<D, V, VM>(
        protected val dataSource: ArchXDataSource<D>,
        protected val transformer: ArchXViewModelTransformer<D, VM>,
        protected val schedulerProvider: SchedulerProvider
) : ArchXPresenter<VM>
        where V : ArchXView<VM> {

    protected var view: V? = null

    private var listeningDisposable: Disposable? = null

    // region ArchXContract.Presenter

    override fun onViewAttached(view: ArchXView<VM>, isRestored: Boolean) {
        this.view = view as? V
        this.view?.bindPresenter(this)

        listeningDisposable?.dispose()
        listeningDisposable = dataSource.listenData()
                .schedule(schedulerProvider)
                .subscribe(
                        { onDataChanged(it) },
                        { onErrorLoadingData(it) }
                )
    }

    override fun onViewDetached() {
        view = null

        listeningDisposable?.dispose()
        listeningDisposable = null
    }

    // endregion

    // region Callbacks

    open fun onDataChanged(t: D) {
        val viewModel = transformer.transform(t)
        view?.showData(viewModel)
    }

    open fun onErrorLoadingData(it: Throwable) {}

    // endregion
}
