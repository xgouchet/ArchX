package fr.xgouchet.archx

import androidx.collection.LruCache

object ArchXPresenterCache {

    private const val MAX_SIZE: Int = 8

    private val cache: LruCache<String, ArchXPresenter<*>> = LruCache(MAX_SIZE)

    fun <P : ArchXPresenter<*>> savePresenter(key: String, presenter: P) {
        cache.put(key, presenter)
    }

    fun <P : ArchXPresenter<*>> getPresenter(key: String, factory: () -> P): P {
        @Suppress("UNCHECKED_CAST")
        val match: P? = cache.get(key) as? P

        return if (match == null) {
            val presenter = factory()
            cache.put(key, presenter)
            presenter
        } else {
            match
        }

    }

    fun dropPresenter(key: String) {
        val removed = cache.remove(key)
        if (removed == null) {
            // Removing presenter, but no match was found… Was it pruned, or never saved ? ¯\_(ツ)_/¯
        }
    }
}