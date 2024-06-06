package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    fun saveOnBoardingState() {
        viewModelScope.launch {
            useCases.saveOnBoardingStateUseCase.invoke(isCompleted = true)
        }
    }

}