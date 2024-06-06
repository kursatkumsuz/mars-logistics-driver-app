package com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.splash_usecases

import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.flow

class IsSignedInUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke() = flow {
        emit(authenticationRepository.isSignedIn())
    }
}