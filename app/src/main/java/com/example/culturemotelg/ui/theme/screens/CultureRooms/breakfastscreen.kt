package com.example.culturemotelg.ui.theme.screens.CultureFoods

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
fun BreakfastScreen(navController: NavController) {
    val breakfastItems = listOf(
        MealItem("Pancakes", 300, R.drawable.pancake),
        MealItem("Omelette", 200, R.drawable.breakfast2),
        MealItem("Fruit Salad", 150, R.drawable.berries),
        MealItem("Smoothie", 100, R.drawable.muffin)
    )

    var selectedMealItem by remember { mutableStateOf<MealItem?>(null) }
    var roomName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Breakfast Menu", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (selectedMealItem != null) {
                            selectedMealItem = null
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
        if (selectedMealItem == null) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                breakfastItems.forEach { item ->
                    MealItemCard(item) { selectedMealItem = it }
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
                    painter = painterResource(id = selectedMealItem!!.imageRes),
                    contentDescription = "${selectedMealItem!!.name} Image",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = selectedMealItem!!.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = "\$${selectedMealItem!!.price}",
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
fun MealItemCard(item: MealItem, onItemClick: (MealItem) -> Unit) {
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

data class MealItem(val name: String, val price: Int, val imageRes: Int)
