package fr.xgouchet.archx.list

import fr.xgouchet.archx.ArchXView

interface ArchXListView<VM> : ArchXView<List<VM>> {
    fun showLoading()
    fun hideLoading()
}