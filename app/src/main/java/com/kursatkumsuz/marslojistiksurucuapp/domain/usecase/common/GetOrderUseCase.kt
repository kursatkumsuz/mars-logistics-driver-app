package com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.common

import com.kursatkumsuz.marslojistiksurucuapp.domain.model.DeliveryOrder
import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.OrderRepository
import com.kursatkumsuz.marslojistiksurucuapp.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(): Flow<Response<List<DeliveryOrder>>> = flow {
        try {
            val data = orderRepository.getDeliveryOrders()
            emit(Response.Loading)
            emit(Response.Success(data))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}