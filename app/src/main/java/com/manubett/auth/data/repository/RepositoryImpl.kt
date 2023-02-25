package com.manubett.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.manubett.auth.core.utils.Resource
import com.manubett.auth.domain.repository.Repository
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): Repository {
    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser>? {
        return try{
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        }catch(e: Exception){
            Resource.Error(e.localizedMessage?: "Unexpected error occurred")
        }
    }

    override suspend fun signup(
        email: String,
        password: String,
        userName: String
    ): Resource<FirebaseUser>? {
        return try{
            val result = auth.createUserWithEmailAndPassword(
                email, password
            ).await()
            result?.user?.updateProfile(userProfileChangeRequest { setDisplayName(userName).build() })?.await()
            Resource.Success(result.user!!)
        }catch(e: Exception){
            Resource.Error(e.localizedMessage?: "Unexpected error occurred")
        }
    }

    override suspend fun logout() {
        return auth.signOut()
    }
}