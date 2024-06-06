package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var onBoardingState by mutableStateOf(false)
        private set

    var signedInState by mutableStateOf(false)
        private set

    init {
        getOnBoardingState()
        getSignedInState()
    }

    private fun getOnBoardingState() {
        viewModelScope.launch {
            onBoardingState = useCases.readOnBoardingStateUseCase().stateIn(viewModelScope).value
        }
    }

    private fun getSignedInState() {
        viewModelScope.launch {
            signedInState = useCases.isSignedInUseCase().stateIn(viewModelScope).value
        }
    }
}