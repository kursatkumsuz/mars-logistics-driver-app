package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.marslojistiksurucuapp.domain.model.DeliveryOrder
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.UseCases
import com.kursatkumsuz.marslojistiksurucuapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var uiState by mutableStateOf(HomeUIState())
        private set

    init {
        getData()
    }

    private fun getData() {
        useCases.getOrderUseCase.invoke().onEach { response ->
                uiState = when (response) {
                    is Response.Success -> {
                        uiState.copy(dataList = response.data)
                    }

                    is Response.Loading -> {
                        uiState.copy(isLoading = true)
                    }

                    is Response.Error -> {
                        uiState.copy(errorMessage = response.errorMessage)
                    }
                }
            }.launchIn(viewModelScope)
    }
}