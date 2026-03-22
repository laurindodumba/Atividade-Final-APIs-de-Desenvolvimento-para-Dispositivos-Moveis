package com.example.api_utfpr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.api_utfpr.LoginScreen.LoginScreen
import com.example.api_utfpr.Home.HomeScreen
import com.example.api_utfpr.ui.theme.APIUTFPRTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            APIUTFPRTheme {

                var isLogged by remember { mutableStateOf(false) }

                if (isLogged) {
                    HomeScreen(
                        onLogout = {
                            isLogged = false
                        }
                    )
                } else {
                    LoginScreen(
                        onLoginSuccess = {
                            println(" LOGIN SUCESSO")
                            isLogged = true
                        }
                    )
                }
            }
        }
    }
}