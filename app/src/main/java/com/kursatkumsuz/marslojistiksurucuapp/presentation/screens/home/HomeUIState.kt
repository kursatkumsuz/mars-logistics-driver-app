package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.home

import com.kursatkumsuz.marslojistiksurucuapp.domain.model.DeliveryOrder

data class HomeUIState(
    val dataList : List<DeliveryOrder>? = null,
    val isLoading : Boolean = false,
    val errorMessage : String = ""
)