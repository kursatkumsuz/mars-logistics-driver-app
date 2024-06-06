package com.kursatkumsuz.marslojistiksurucuapp.domain.usecase.signin_usecase

import com.kursatkumsuz.marslojistiksurucuapp.util.Response
import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val authRepository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) = flow {
        try {
            emit(Response.Loading)
            emit(Response.Success(authRepository.signInWithEmailAndPassword(email, password).await())
            )
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}