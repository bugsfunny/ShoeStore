package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel: ViewModel() {
    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList
    init {
        _shoeList.value = mutableListOf()
    }
    fun addShoe(shoe: Shoe){
        Timber.i(_shoeList.value.toString())
        _shoeList.value?.add(shoe)
        Timber.i(_shoeList.value.toString())
    }
}