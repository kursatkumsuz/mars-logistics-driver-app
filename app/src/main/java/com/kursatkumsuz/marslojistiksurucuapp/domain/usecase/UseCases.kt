package com.kursatkumsuz.marslojistiksurucuapp.domain.usecase

import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.common.GetOrderUseCase
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.signin_usecase.SaveOnBoardingStateUseCase
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.signin_usecase.SignInWithEmailAndPasswordUseCase
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.splash_usecases.IsSignedInUseCase
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.splash_usecases.ReadOnBoardingStateUseCase

data class UseCases (
    val saveOnBoardingStateUseCase: SaveOnBoardingStateUseCase,
    val readOnBoardingStateUseCase: ReadOnBoardingStateUseCase,
    val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    val isSignedInUseCase: IsSignedInUseCase,
    val getOrderUseCase: GetOrderUseCase
)