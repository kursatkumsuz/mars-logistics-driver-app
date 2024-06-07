package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.detail

import com.kursatkumsuz.marslojistiksurucuapp.domain.model.DeliveryOrder

data class DetailUIState (
    val order : DeliveryOrder? = null,
    val isLoading : Boolean = false,
    val error : String = ""
)