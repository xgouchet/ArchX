package fr.xgouchet.archx.data

import androidx.lifecycle.MutableLiveData

interface ArchXMutableDataSource<D> : ArchXDataSource<D> {

    fun getMutableData() : MutableLiveData<D>

}