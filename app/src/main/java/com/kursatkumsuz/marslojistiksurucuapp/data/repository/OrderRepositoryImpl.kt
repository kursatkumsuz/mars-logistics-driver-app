package com.kursatkumsuz.marslojistiksurucuapp.data.repository

import com.kursatkumsuz.marslojistiksurucuapp.data.remote.ApiService
import com.kursatkumsuz.marslojistiksurucuapp.domain.model.DeliveryOrder
import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.OrderRepository

class OrderRepositoryImpl(private val apiService: ApiService) : OrderRepository {
    override suspend fun getDeliveryOrders(): List<DeliveryOrder> {
        return apiService.getDeliveryOrders()
    }
}