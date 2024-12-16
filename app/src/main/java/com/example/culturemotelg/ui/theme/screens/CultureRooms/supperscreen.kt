package com.example.culturemotelg.ui.theme.screens.CultureRooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.culturemotelg.R
import com.example.culturemotelg.navigation.ROUTE_PAY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupperScreen(navController: NavController) {
    val supperItems = listOf(
        DishItem("Steak and Potatoes", 600, R.drawable.ai1),
        DishItem("Salmon with Veggies", 500, R.drawable.supper),
        DishItem("Veggie Stir-fry", 400, R.drawable.ai4),
        DishItem("Beef Tacos", 350, R.drawable.chicken)
    )

    var selectedDishItem by remember { mutableStateOf<DishItem?>(null) }
    var roomName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Supper Menu", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (selectedDishItem != null) {
                            selectedDishItem = null
                        } else {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (selectedDishItem == null) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SpecialOfferSection()
                supperItems.forEach { item ->
                    DishItemCard(item) { selectedDishItem = it }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = selectedDishItem!!.imageRes),
                    contentDescription = "${selectedDishItem!!.name} Image",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = selectedDishItem!!.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = "\$${selectedDishItem!!.price}",
                    fontSize = 20.sp
                )
                OutlinedTextField(
                    value = roomName,
                    onValueChange = { roomName = it },
                    label = { Text("Room Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = { /* Implement order logic */ },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Order")
                }
                Button(
                    onClick = {navController.navigate(ROUTE_PAY) },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Pay Now")
                }
            }
        }
    }
}

@Composable
fun SpecialOfferSection() {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Today's Special Offer",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Get 20% off on orders above \$500!",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun DishItemCard(item: DishItem, onItemClick: (DishItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onItemClick(item) },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "${item.name} Image",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "\$${item.price}",
                    fontSize = 16.sp
                )
            }
        }
    }
}

data class DishItem(val name: String, val price: Int, val imageRes: Int)
