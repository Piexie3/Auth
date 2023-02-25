package com.manubett.auth.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manubett.auth.presentation.home.HomeScreen
import com.manubett.auth.presentation.login.LoginScreen
import com.manubett.auth.presentation.signup.SignUpScreen
import com.manubett.auth.presentation.viewModel.AuthViewModel

@Composable
fun NavGraph(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.LogInScreen.route
    ){
        composable(Screens.LogInScreen.route){
            LoginScreen(viewModel, navController)
        }
        composable(Screens.SignUpScreen.route){
            SignUpScreen(viewModel, navController)
        }
        composable(Screens.HomeScreen.route){
            HomeScreen(viewModel, navController)
        }
    }
}