package fr.xgouchet.archx.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import fr.xgouchet.archx.ArchXPresenter
import fr.xgouchet.archx.R
import fr.xgouchet.archx.ui.ArchXAdapter

abstract class ArchXListFragment<VM, A>
    : Fragment(),
        SwipeRefreshLayout.OnRefreshListener,
        ArchXListView<VM>
        where A : ArchXAdapter<*, *> {

    protected var presenter: ArchXPresenter<List<VM>>? = null

    protected lateinit var contentView: View

    protected var recyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager

    protected lateinit var adapter: A
        private set


    // region Open / Abstract Properties
    /**
     * You can override this with the layout of your choice, but make sure it has at least the following element :
     *  - a RecyclerView with id `archx_recycler`
     *  Optional elements are :
     *  - a SwipeRefreshLayout with id `archx_swipe_refresh`
     */
    open val layout: Int? = null

    open val userCanRefresh: Boolean = false
    open val accentColor: Int? = null
    open val dividerDrawable: Int? = null

    protected abstract val hasPagination: Boolean

    // endregion

    // region Fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(layout ?: R.layout.fragment_archx_list, container, false)

        internalBindViews(rootView)
        bindViews(rootView)

        internalSetupViews()
        setupViews()

        return rootView
    }

    // endregion

    // region OnRefreshListener

    override fun onRefresh() {}

    // endregion

    // region ArchXListView

    override fun bindPresenter(presenter: ArchXPresenter<List<VM>>) {
        this.presenter = presenter
    }

    override fun showLoading() {
        swipeRefreshLayout?.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout?.isRefreshing = true
    }

    // endregion

    // region Open/Abstract functions

    open fun onLoadNextPage() {}

    open fun bindViews(rootView: View) {}

    open fun setupViews() {}

    abstract fun createAdapter(): A

    // endregion

    // region Internal

    private fun internalBindViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.archx_recycler)
        swipeRefreshLayout = rootView.findViewById(R.id.archx_swipe_refresh)

        contentView = rootView
    }

    private fun internalSetupViews() {

        adapter = createAdapter()

        recyclerView?.let {
            layoutManager = createLayoutManager(it.context)
            setupRecyclerView(it)
        }

        swipeRefreshLayout?.let {
            setupSwipeRefreshLayout(this.accentColor, it)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        val dividerDrawable = this.dividerDrawable
        if (dividerDrawable != null) {
            val dividerItemDecoration = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(recyclerView.context, dividerDrawable)
                    ?.let { dividerItemDecoration.setDrawable(it) }
            recyclerView.addItemDecoration(dividerItemDecoration)
        }

        if (hasPagination) {
            // TODO recyclerView.addOnScrollListener(PaginationScrollListener(this, layoutManager))
        }
    }

    private fun setupSwipeRefreshLayout(accentColor: Int?, it: SwipeRefreshLayout) {
        if (accentColor != null) {
            it.setColorSchemeColors(accentColor)
        }

        if (userCanRefresh) {
            it.setOnRefreshListener(this)
            it.isEnabled = true
        } else {
            it.isEnabled = false
        }
    }

    private fun createLayoutManager(context: Context): RecyclerView.LayoutManager {
        val maxSpan = adapter.getMaxSpanSize()
        val gridLayoutManager = GridLayoutManager(context, maxSpan)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return adapter.getSpanSize(position)
            }
        }
        return gridLayoutManager
    }

    // endregion

}