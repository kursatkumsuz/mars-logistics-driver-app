package com.kursatkumsuz.marslojistiksurucuapp.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(private val firebaseAuth: FirebaseAuth) : AuthenticationRepository {

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun isSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}