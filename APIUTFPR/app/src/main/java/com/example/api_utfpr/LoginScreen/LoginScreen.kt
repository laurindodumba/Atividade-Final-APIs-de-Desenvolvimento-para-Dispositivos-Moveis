package com.example.api_utfpr.LoginScreen

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.api_utfpr.Auth.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = AuthViewModel(),
    onLoginSuccess: () -> Unit
) {

    val context = LocalContext.current as Activity

    var phone by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Login com Telefone")

        Spacer(modifier = Modifier.height(16.dp))

        // 📱 Campo telefone
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Telefone") },
            placeholder = { Text("+5511912345678") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔐 Campo código
        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("Código") },
            placeholder = { Text("123456") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {

            //  1. envia código
            viewModel.sendCode(
                phone,
                context,
                onCodeSent = {
                    verificationId = it

                    // 2. já valida com código
                    viewModel.verifyCode(
                        verificationId,
                        code,
                        onSuccess = {
                            onLoginSuccess()
                        },
                        onError = {}
                    )
                },
                onError = {}
            )

        }) {
            Text("Entrar")
        }
    }
}