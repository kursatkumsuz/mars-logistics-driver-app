package com.kursatkumsuz.marslojistiksurucuapp.di

import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.AuthenticationRepository
import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.DataStoreRepository
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.signin_usecase.SaveOnBoardingStateUseCase
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.splash_usecases.ReadOnBoardingStateUseCase
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.UseCases
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.signin_usecase.SignInWithEmailAndPasswordUseCase
import com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.splash_usecases.IsSignedInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCases(
        dataStoreRepository: DataStoreRepository,
        authenticationRepository: AuthenticationRepository
    ): UseCases {
        return UseCases(
            saveOnBoardingStateUseCase = SaveOnBoardingStateUseCase(dataStoreRepository = dataStoreRepository),
            readOnBoardingStateUseCase = ReadOnBoardingStateUseCase(dataStoreRepository = dataStoreRepository),
            signInWithEmailAndPasswordUseCase = SignInWithEmailAndPasswordUseCase(authRepository = authenticationRepository),
            isSignedInUseCase = IsSignedInUseCase(authenticationRepository = authenticationRepository)
        )
    }
}