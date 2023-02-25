package com.manubett.auth.di

import com.google.firebase.auth.FirebaseAuth
import com.manubett.auth.data.repository.RepositoryImpl
import com.manubett.auth.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesRepository(impl : RepositoryImpl): Repository =impl

}