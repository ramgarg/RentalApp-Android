package com.eazyrento.merchant.model.modelclass

data class AvailabilityDays(
    var fri: Int,
    var mon: Int,
    var sat: Int,
    var sun: Int,
    var thu: Int,
    var tue: Int,
    var wed: Int
){
    constructor():this(0,0,0,0,0,0,0)
}