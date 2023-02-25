package com.manubett.auth.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.manubett.auth.core.utils.Resource

interface Repository {
    val currentUser : FirebaseUser?
    suspend fun login(email:String,password:String):Resource<FirebaseUser>?
    suspend fun signup(email:String,password:String,userName:String):Resource<FirebaseUser>?
    suspend fun logout()
}