package com.example.culturemotelg.ui.theme.screens.CustomerCare

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerCareScreen(navController: NavController) {
    var message by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<String>() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Customer Care", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Chat with Us", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    messages.forEach { msg ->
                        Text(
                            text = msg,
                            modifier = Modifier.padding(4.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Enter your message") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    if (message.isNotEmpty()) {
                        coroutineScope.launch {
                            messages.add("You: $message")
                            // Here you would send the message to the customer care representative
                            message = ""
                        }
                    }
                })
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (message.isNotEmpty()) {
                        messages.add("You: $message")
                        // Here you would send the message to the customer care representative
                        message = ""
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Send")
            }
        }
    }
}
