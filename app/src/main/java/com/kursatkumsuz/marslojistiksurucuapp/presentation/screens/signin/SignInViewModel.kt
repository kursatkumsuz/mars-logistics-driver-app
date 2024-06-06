package com.kursatkumsuz.marslojistiksurucuapp.presentation.screens.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.UseCases
import com.kursatkumsuz.marslojistiksurucuapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var state by mutableStateOf(SignInUIState())
        private set

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        useCases.signInWithEmailAndPasswordUseCase.invoke(email, password)
            .onEach { result ->
                state = when (result) {
                    is Response.Success -> {
                        onSuccess()
                        SignInUIState(isAuthenticated = true)
                    }

                    is Response.Loading -> {
                        SignInUIState(isLoading = true)
                    }

                    is Response.Error -> {
                        onError(result.errorMessage)
                        SignInUIState()
                    }
                }
            }.launchIn(viewModelScope)
    }
}