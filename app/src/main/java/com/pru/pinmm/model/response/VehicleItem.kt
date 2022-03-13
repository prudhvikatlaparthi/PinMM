package com.pru.pinmm.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleItem(
    var vehId: Int?,
    var vehicleRegNo: String?,
    var vehicleType: String?,
    var isSelected: Boolean = false
) : Parcelable
