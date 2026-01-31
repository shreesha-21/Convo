package com.example.convo.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.convo.R
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreen(
    onLoginClick: (String) -> Unit, // Callback to send the name back
    viewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    // username stores the username
    val username by viewModel.username.collectAsStateWithLifecycle()
    var showSnackbar: Boolean = false

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is LoginViewModel.UiEvent.NavigateToChatList -> {
                    // Trigger the actual navigation callback
                    onLoginClick(event.username)
                }
                is LoginViewModel.UiEvent.ShowSnackbar -> {
                    Log.d("LOGIN_SCREEN", "Failed to navigate to the chatList screen")
                    showSnackbar = true;
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary) // Top half Purple
    ) {
        // --- Top Section: Header ---
        Box(
            modifier = Modifier
                .weight(1f) // Takes up top space
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(start = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.applogo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.login_screen_text),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.enter_details_prompt),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.8f)
                )
                if (showSnackbar) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Snackbar(modifier = Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.snackbar_text))
                    }
                }
            }
        }

        // --- Bottom Section: Input Form ---
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp), // The signature curve
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Input Field
                OutlinedTextField(
                    value = username,
                    onValueChange = { viewModel.onUsernameChange(it) },
                    label = { Text(stringResource(R.string.username)) },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = {
                        if (username.isNotBlank()) {
                            viewModel.login()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    enabled = username.isNotBlank() // Disable if empty
                ) {
                    Text(
                        text = stringResource(R.string.login_button_text),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewLogin() {
    LoginScreen(onLoginClick = {})
}