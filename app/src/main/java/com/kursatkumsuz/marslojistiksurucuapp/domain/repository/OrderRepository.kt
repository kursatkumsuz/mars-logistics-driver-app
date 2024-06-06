package com.kursatkumsuz.marslojistiksurucuapp.domain.repository

import com.kursatkumsuz.marslojistiksurucuapp.domain.model.DeliveryOrder

interface OrderRepository {
    suspend fun getDeliveryOrders(): List<DeliveryOrder>
}