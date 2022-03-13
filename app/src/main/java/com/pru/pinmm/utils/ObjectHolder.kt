package com.pru.pinmm.utils

import com.pru.pinmm.model.response.VehicleItem

object ObjectHolder {
    private val vehiclesList: ArrayList<VehicleItem> = arrayListOf()

    fun resetVehicles() {
        vehiclesList.clear()
    }

    fun setVehicles(list: ArrayList<VehicleItem>) {
        vehiclesList.addAll(list)
    }

    fun getVehicles(): ArrayList<VehicleItem> = vehiclesList
}