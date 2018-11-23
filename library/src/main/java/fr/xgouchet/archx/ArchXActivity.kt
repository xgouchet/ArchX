package fr.xgouchet.archx

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

abstract class ArchXActivity<P, V, VM>
    : AppCompatActivity()
        where P : ArchXPresenter<VM>,
              V : ArchXView<VM> {


    protected lateinit var fragment: V
        private set

    protected lateinit var presenter: P
        private set

    protected var isRestored: Boolean = false
        private set

    protected lateinit var fab: FloatingActionButton
        private set


    // region Activity Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isSensitive()) {
            window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        }

        setContentView(R.layout.activity_archx_single_fragment)
        fab = findViewById(R.id.archx_fab)
        val iconRes = getFabIcon()
        if (iconRes != null && iconRes != 0) {
            fab.visibility = View.VISIBLE
            fab.setImageResource(iconRes)
            fab.setOnClickListener { onFabClicked() }
        }

        intent?.let { handleIntent(it) }
        if (isFinishing) return

        if (savedInstanceState != null) {
            isRestored = true
            presenter = restorePresenter(savedInstanceState)
            val rawFragment = supportFragmentManager.findFragmentById(R.id.archx_root_fragment)
            @Suppress("UNCHECKED_CAST")
            fragment = rawFragment as V
        } else {
            presenter = instantiatePresenter()
            val view = instantiateFragment()
            require(view is Fragment)
            fragment = view
            supportFragmentManager.beginTransaction()
                    .add(R.id.archx_root_fragment, view as Fragment)
                    .commit()
        }
    }

    override fun onRestart() {
        super.onRestart()
        isRestored = true
    }

    override fun onStart() {
        super.onStart()
        presenter.onViewAttached(fragment, isRestored)
    }

    override fun onStop() {
        super.onStop()
        presenter.onViewDetached()

        if (isFinishing) {
            val key = getPresenterKey()
            ArchXPresenterCache.dropPresenter(key)
        }
    }

    // endregion

    // region State Lifecycle

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            if (isRestored) {
                // Already restored but restoring again ‽
                return
            }
            isRestored = true
            val restoredPresenter = restorePresenter(savedInstanceState)
            if (restoredPresenter !== presenter) {
                presenter.onViewDetached()
                presenter = restoredPresenter
                presenter.onViewAttached(fragment, isRestored)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val key = getPresenterKey()
        ArchXPresenterCache.savePresenter(key, presenter)
        outState?.putString(PRESENTER_KEY, key)
    }

    // endregion

    // region Internal

    private fun restorePresenter(savedInstanceState: Bundle): P {
        val key: String? = savedInstanceState.getString(PRESENTER_KEY, null)

        if (key == null) {
            // Expected to restore presenter, but not key found ¯\_(ツ)_/¯
            return instantiatePresenter()
        }

        return ArchXPresenterCache.getPresenter(key) { instantiatePresenter() }
    }

    // endregion

    // region Abstract

    abstract fun instantiatePresenter(): P

    abstract fun instantiateFragment(): V

    abstract fun getPresenterKey(): String

    // endregion

    // region Open

    open fun handleIntent(intent: Intent) {}

    open fun isSensitive(): Boolean = false

    open fun onFabClicked() {}

    open fun getFabIcon(): Int? = null

    // endregion

    companion object {
        private const val PRESENTER_KEY = "presenter_key"
    }
}
