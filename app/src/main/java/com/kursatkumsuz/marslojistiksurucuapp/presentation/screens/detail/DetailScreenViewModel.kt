package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.UseCases
import com.kursatkumsuz.marslojistiksurucuapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val orderNo = savedStateHandle.get<Long>("orderNo")

    var uiState by mutableStateOf(DetailUIState())
        private set

    init {
        getOrder()
    }

    private fun getOrder() {
        useCases.getOrderUseCase.invoke().onEach { response ->
            when (response) {
                is Response.Success -> {
                    val filteredOrder = response.data.firstOrNull { it.orderNo == orderNo }
                    uiState = uiState.copy(order = filteredOrder)
                }

                is Response.Loading -> {
                    uiState = uiState.copy(isLoading = true)
                }

                is Response.Error -> {
                    uiState = uiState.copy(error = response.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }
}