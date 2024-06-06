package com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.signin_usecase

import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveOnBoardingStateUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) {
        dataStoreRepository.saveOnBoardingState(isCompleted = isCompleted)
    }
}