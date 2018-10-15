package fr.xgouchet.archx.data

import androidx.lifecycle.LiveData

interface ArchXDataSource<D> {

    fun getData(): LiveData<D>

}