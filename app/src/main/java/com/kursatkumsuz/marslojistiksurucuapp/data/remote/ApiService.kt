package com.kursatkumsuz.marslojistiksurucuapp.data.remote

import com.kursatkumsuz.marslojistiksurucuapp.domain.model.DeliveryOrder
import retrofit2.http.GET

interface ApiService {
    @GET("/news")
    suspend fun getDeliveryOrders(): List<DeliveryOrder>

}