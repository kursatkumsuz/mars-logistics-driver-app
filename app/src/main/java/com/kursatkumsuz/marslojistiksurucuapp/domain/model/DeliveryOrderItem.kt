package com.kursatkumsuz.marslojistiksurucuapp.domain.model

import com.google.gson.annotations.SerializedName

data class DeliveryOrder(
    @SerializedName("order_no") val orderNo: Long? = null,
    @SerializedName("unit") val unit: String? = null,
    @SerializedName("departure_point_name") val departurePointName: String? = null,
    @SerializedName("arrival_point_name") val arrivalPointName: String? = null,
    @SerializedName("coordinates") val coordinates: List<List<Double>>? = null,
    @SerializedName("in_transit") val inTransit: Boolean? = null,
    @SerializedName("customer_name") val customerName: String? = null,
    @SerializedName("customer_phone") val customerPhone: String? = null,
    @SerializedName("courier_note") val courierNote: String? = null,
    @SerializedName("date") val date: String? = null
)
