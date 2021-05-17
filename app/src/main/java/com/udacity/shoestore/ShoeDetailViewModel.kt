package com.udacity.shoestore

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import java.math.BigDecimal
import java.math.RoundingMode

class ShoeDetailViewModel: ViewModel(), Observable {
    private val propertyChangeRegistry = PropertyChangeRegistry()

    @Bindable
    var shoe = Shoe()
    set(value) {
        if (value != field) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.shoe)
        }
    }

    fun addSize(){
        shoe.size = (BigDecimal(shoe.size + 0.1)).setScale(1,RoundingMode.HALF_DOWN).toDouble()
        propertyChangeRegistry.notifyChange(this, BR.shoe)
    }
    fun removeSize(){
        if(shoe.size > 0){
            shoe.size = (BigDecimal(shoe.size - 0.1)).setScale(1,RoundingMode.HALF_DOWN).toDouble()
            propertyChangeRegistry.notifyChange(this, BR.shoe)
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }

}