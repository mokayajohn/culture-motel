package com.example.culturemotelg.ui.theme.screens.CultureRooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.culturemotelg.R
import com.example.culturemotelg.navigation.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CultureRoomsScreen(navController: NavController) {
    var showPasswordDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Culture Rooms", fontSize = 20.sp) }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Rooms Section
            Text(text = "Book a Room", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.wrapContentWidth()) {
                RoomCard(navController, "VIP", R.drawable.sleeping, ROUTE_VIP_ROOMS_DETAIL)
                RoomCard(navController, "VVIP", R.drawable.sleeping, ROUTE_VVIP_ROOMS_DETAIL)
                RoomCard(navController, "MASTERCLASS", R.drawable.sleeping, ROUTE_MASTERCLASS_ROOMS_DETAIL)
            }

            // Divider
            Spacer(modifier = Modifier.height(16.dp))

            // Food Section
            Text(text = "Order Food", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.wrapContentWidth()) {
                FoodCard(navController, "Breakfast", R.drawable.breakfast, ROUTE_BREAKFAST_SCREEN)
                FoodCard(navController, "Lunch", R.drawable.meatloaf, ROUTE_LUNCH_SCREEN)
                FoodCard(navController, "Dinner", R.drawable.supper, ROUTE_SUPPER_SCREEN)
            }

            // Divider
            Spacer(modifier = Modifier.height(16.dp))

            // Customer Care Button
            Button(
                onClick = { navController.navigate(ROUTE_CONSTUMERCARE_SCREEN) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Customer Care")
            }

            // Divider
            Spacer(modifier = Modifier.height(16.dp))

            // Admin Dashboard Button
            Button(
                onClick = { showPasswordDialog = true },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Admin Dashboard")
            }

            if (showPasswordDialog) {
                PasswordDialog(
                    onDismiss = { showPasswordDialog = false },
                    onPasswordSubmit = { enteredPassword ->
                        if (enteredPassword == "3244") {
                            navController.navigate(ROUTE_HOME)
                        } else {
                            // Handle incorrect password
                        }
                    }
                )
            }

            // Divider
            Spacer(modifier = Modifier.height(16.dp))

            // Parking Availability
            ParkingAvailability(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomCard(navController: NavController, text: String, imageRes: Int, route: String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { navController.navigate(route) },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(modifier = Modifier.height(70.dp)) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = text
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(11.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = Color.Black,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    text = text
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodCard(navController: NavController, text: String, imageRes: Int, route: String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { navController.navigate(route) },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(modifier = Modifier.height(70.dp)) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = text
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(11.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = Color.Black,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    text = text
                )
            }
        }
    }
}

@Composable
fun ParkingAvailability(navController: NavController) {
    var showParkingImages by remember { mutableStateOf(false) }
    Column {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .clickable { navController.navigate(ROUTE_PARKING_SCREEN) },
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Box(modifier = Modifier.height(70.dp), contentAlignment = Alignment.Center) {
                Text(
                    text = "Parking is Available",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green
                )
            }
        }
        if (showParkingImages) {
            Spacer(modifier = Modifier.height(16.dp))
            ParkingImages()
        }
    }
}

@Composable
fun ParkingImages() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.parking),
            contentDescription = "Staff Parking",
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.cars1),
            contentDescription = "Underground Parking",
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.cars2),
            contentDescription = "Open Space Parking",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PasswordDialog(onDismiss: () -> Unit, onPasswordSubmit: (String) -> Unit) {
    var password by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Enter Admin Password") },
        text = {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(onClick = {
                onPasswordSubmit(password)
                onDismiss()
            }) {
                Text("Submit")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
