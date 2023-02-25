package com.manubett.auth.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import  com.manubett.auth.R
import androidx.navigation.NavController
import com.manubett.auth.presentation.viewModel.AuthViewModel

@Composable
fun HomeScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile picture",
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: ${viewModel.currentUser?.displayName}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Email: ${viewModel.currentUser?.email}")
        TextButton(onClick = { }) {
            Text(text = "Log out")
        }
    }
}