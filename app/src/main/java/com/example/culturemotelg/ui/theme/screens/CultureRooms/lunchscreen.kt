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
fun LunchScreen(navController: NavController) {
    val lunchItems = listOf(
        FoodItem("Grilled Chicken", 500, R.drawable.chicken),
        FoodItem("Caesar Salad", 300, R.drawable.ai4),
        FoodItem("Spaghetti Bolognese", 400, R.drawable.ai1),
        FoodItem("Burger and Fries", 350, R.drawable.pasta)
    )

    var selectedFoodItem by remember { mutableStateOf<FoodItem?>(null) }
    var roomName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lunch Menu", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (selectedFoodItem != null) {
                            selectedFoodItem = null
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
        if (selectedFoodItem == null) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                lunchItems.forEach { item ->
                    FoodItemCard(item) { selectedFoodItem = it }
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
                    painter = painterResource(id = selectedFoodItem!!.imageRes),
                    contentDescription = "${selectedFoodItem!!.name} Image",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = selectedFoodItem!!.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = "\$${selectedFoodItem!!.price}",
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
                    onClick = { navController.navigate(ROUTE_PAY) },
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
fun FoodItemCard(item: FoodItem, onItemClick: (FoodItem) -> Unit) {
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

data class FoodItem(val name: String, val price: Int, val imageRes: Int)
