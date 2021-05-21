package com.udacity.shoestore

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode

class ShoeListViewModel: ViewModel(), Observable {
    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    private val propertyChangeRegistry = PropertyChangeRegistry()
    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    init {
        _shoeList.value = mutableListOf()
    }

    @Bindable
    var shoe = Shoe()
        set(value) {
            if (value != field) {
                field = value
                propertyChangeRegistry.notifyChange(this, BR.shoe)
            }
        }

    fun addSize(){
        shoe.size = (BigDecimal(shoe.size + 0.1)).setScale(1, RoundingMode.HALF_DOWN).toDouble()
        propertyChangeRegistry.notifyChange(this, BR.shoe)
    }
    fun removeSize(){
        if(shoe.size > 0){
            shoe.size = (BigDecimal(shoe.size - 0.1)).setScale(1, RoundingMode.HALF_DOWN).toDouble()
            propertyChangeRegistry.notifyChange(this, BR.shoe)
        }
    }

    fun addShoe(shoe: Shoe){
        Timber.i(_shoeList.value.toString())
        _shoeList.value?.add(shoe)
        Timber.i(_shoeList.value.toString())
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }
}