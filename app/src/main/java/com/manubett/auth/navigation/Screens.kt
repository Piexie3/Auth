package com.manubett.auth.navigation

sealed class Screens(val route:String){
    object HomeScreen : Screens("Home_screen")
    object LogInScreen : Screens("LogIn_screen")
    object SignUpScreen : Screens("SignUp_screen")
}


