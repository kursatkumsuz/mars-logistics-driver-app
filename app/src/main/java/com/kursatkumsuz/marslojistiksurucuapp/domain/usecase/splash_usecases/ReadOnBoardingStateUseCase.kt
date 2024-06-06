package com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.splash_usecases

import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.DataStoreRepository

class ReadOnBoardingStateUseCase(
    private val dataStoreRepository: DataStoreRepository
) {

    operator fun invoke() = dataStoreRepository.readOnBoardingState()
}