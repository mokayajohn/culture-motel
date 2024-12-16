package com.example.culturemotelg.ui.theme.screens.CultureRooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.culturemotelg.R
import com.example.culturemotelg.navigation.ROUTE_PAY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasterclassRoomsScreen(navController: NavController) {
    val rooms = listOf(
        MasterclassRoomDetail("Hilltop Masterclass", 500, 4, R.drawable.bedroom5, R.drawable.livingroom6, R.drawable.kitchen, R.drawable.bathroom6),
        MasterclassRoomDetail("Safari lodge Masterclass ", 600, 4, R.drawable.bedroom6, R.drawable.livingroom5, R.drawable.kitchen, R.drawable.bathroom5),
        MasterclassRoomDetail("Paradise Masterclass", 700, 4, R.drawable.bedroom4, R.drawable.livingroom3, R.drawable.kitchen, R.drawable.bathroom3),
        MasterclassRoomDetail("Amazon Masterclass", 800, 4, R.drawable.bedroom3, R.drawable.livingroom4, R.drawable.kitchen, R.drawable.bathroom2),
        MasterclassRoomDetail("Royal Chamber Masterclass", 900, 4, R.drawable.bed2, R.drawable.livingroom7, R.drawable.kitchen, R.drawable.bathroom1)
    )

    var selectedRoom by remember { mutableStateOf<MasterclassRoomDetail?>(null) }
    var selectedImageIndex by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Masterclass Rooms", fontSize = 20.sp) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (selectedRoom == null) {
                rooms.forEach { room ->
                    MasterclassRoomCard(room) { selectedRoom = it }
                }
            } else {
                if (selectedImageIndex != -1) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = when (selectedImageIndex) {
                                0 -> selectedRoom!!.pic1
                                1 -> selectedRoom!!.pic2
                                else -> selectedRoom!!.pic3
                            }),
                            contentDescription = "Full screen image",
                            modifier = Modifier.fillMaxSize()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { if (selectedImageIndex > 0) selectedImageIndex-- }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Previous Image")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { if (selectedImageIndex < 2) selectedImageIndex++ }) {
                                Icon(Icons.Default.ArrowForward, contentDescription = "Next Image")
                            }
                            IconButton(onClick = { selectedImageIndex = -1 }) {
                                Icon(Icons.Default.Close, contentDescription = "Close")
                            }
                        }
                    }
                } else {
                    MasterclassRoomFullDetail(
                        room = selectedRoom!!,
                        onBackClick = { selectedRoom = null },
                        onImageClick = { selectedImageIndex = it },
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun MasterclassRoomCard(room: MasterclassRoomDetail, onRoomClick: (MasterclassRoomDetail) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onRoomClick(room) },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = room.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Rent per day: \$${room.rentPerDay}",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Maximum number of people: ${room.maxPeople}",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun MasterclassRoomFullDetail(room: MasterclassRoomDetail, onBackClick: () -> Unit, onImageClick: (Int) -> Unit, navController: NavController) {
    var startDate by remember { mutableStateOf(TextFieldValue("")) }
    var endDate by remember { mutableStateOf(TextFieldValue("")) }
    var numberOfDays by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = room.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = room.pic1),
                contentDescription = "${room.name} Picture 1",
                modifier = Modifier
                    .weight(1f)
                    .clickable { onImageClick(0) }
            )
            Image(
                painter = painterResource(id = room.pic2),
                contentDescription = "${room.name} Picture 2",
                modifier = Modifier
                    .weight(1f)
                    .clickable { onImageClick(1) }
            )
            Image(
                painter = painterResource(id = room.pic3),
                contentDescription = "${room.name} Picture 3",
                modifier = Modifier
                    .weight(1f)
                    .clickable { onImageClick(2) }
            )
        }
        Text(
            text = "Rent per day: \$${room.rentPerDay}",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Maximum number of people: ${room.maxPeople}",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Small Kitchen Area",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Start Date Field
        OutlinedTextField(
            value = startDate,
            onValueChange = { startDate = it },
            label = { Text("Start Date") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // End Date Field
        OutlinedTextField(
            value = endDate,
            onValueChange = { endDate = it },
            label = { Text("End Date") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Number of Days Field
        OutlinedTextField(
            value = numberOfDays,
            onValueChange = { numberOfDays = it },
            label = { Text("Number of Days") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {navController.navigate(ROUTE_PAY)},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Pay Now")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onBackClick() },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Back")
        }
    }
}

data class MasterclassRoomDetail(val name: String, val rentPerDay: Int, val maxPeople: Int, val pic1: Int, val pic2: Int, val pic3: Int, val bathroomPic: Int)
