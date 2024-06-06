package com.kursatkumsuz.marslojistiksurucuapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.kursatkumsuz.marslojistiksurucuapp.data.repository.AuthenticationRepositoryImpl
import com.kursatkumsuz.marslojistiksurucuapp.data.repository.DataStoreRepositoryImpl
import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.AuthenticationRepository
import com.kursatkumsuz.marslojistiksurucuapp.domain.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(firebaseAuth: FirebaseAuth): AuthenticationRepository {
        return AuthenticationRepositoryImpl(firebaseAuth = firebaseAuth)
    }

}