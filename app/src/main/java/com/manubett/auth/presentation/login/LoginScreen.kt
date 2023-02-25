package com.manubett.auth.presentation.login

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.manubett.auth.core.utils.Resource
import com.manubett.auth.navigation.Screens
import com.manubett.auth.presentation.viewModel.AuthViewModel


@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    navController: NavController,
) {
    Column() {
        val context = LocalContext.current
        val loginFlow = viewModel.loginFlow.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val focusManager = LocalFocusManager.current

            var email by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }

            val isEmailValid by remember{
                derivedStateOf {
                    Patterns.EMAIL_ADDRESS.matcher(email).matches()
                }
            }

            val isPasswordValid by remember{
                derivedStateOf {
                    password.length > 7 }
            }

            var isPasswordVisible by remember {
                mutableStateOf(false)
            }

            Text(
                text = "LogIn",
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 50.sp,

                )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email Address") },
                placeholder = { Text(text = "abc@domain.com") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Address"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = !isEmailValid,
                trailingIcon = {
                    if (email.isNotBlank()) {
                        IconButton(
                            onClick = { email = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear email"
                            )
                        }
                    }
                },
                singleLine = true,
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Email Address"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = !isPasswordValid,
                trailingIcon = {
                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password Visibility"
                        )
                    }
                }
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Forgotten Password?",
                    modifier = Modifier
                        .clickable {

                        },
                    color = Color.Blue
                )
            }

            Button(
                onClick = {
                    viewModel.login(email, password)
                },
                enabled = isEmailValid && isPasswordValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Green)
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            loginFlow.value.let {
                when (it) {
                    is Resource.Success -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(Screens.HomeScreen.route){
                                popUpTo(Screens.HomeScreen.route){
                                    inclusive = true
                                }
                            }
                        }
                    }
                    is Resource.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp)
                                .border(
                                    shape = MaterialTheme.shapes.medium,
                                    color = MaterialTheme.colorScheme.primary,
                                    width = 2.dp
                                )
                        )
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    else -> {

                    }
                }
            }


            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = {
                        navController.navigate(Screens.SignUpScreen.route){
                            popUpTo(Screens.SignUpScreen.route){
                                inclusive = true
                            }
                        }
                    },
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Don't have an account?")
                            append(" ")
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Sign Up")
                            }
                        },
                        textAlign = TextAlign.Center
                    )
                }
            }

        }

    }
}
