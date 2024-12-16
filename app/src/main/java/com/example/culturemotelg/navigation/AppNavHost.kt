package com.example.culturemotelg.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.culturemotelg.ui.theme.screens.Clients.AddClientScreen
import com.example.culturemotelg.ui.theme.screens.Clients.PaymentScreen
import com.example.culturemotelg.ui.theme.screens.Clients.UpdateClientScreen
import com.example.culturemotelg.ui.theme.screens.Clients.VIPRoomsDetailScreen
import com.example.culturemotelg.ui.theme.screens.Clients.ViewClients
import com.example.culturemotelg.ui.theme.screens.CultureFoods.BreakfastScreen
import com.example.culturemotelg.ui.theme.screens.CultureRooms.CultureRoomsScreen
import com.example.culturemotelg.ui.theme.screens.CultureRooms.FullscreenImageScreen
import com.example.culturemotelg.ui.theme.screens.CultureRooms.LunchScreen
import com.example.culturemotelg.ui.theme.screens.CultureRooms.MasterclassRoomsScreen
import com.example.culturemotelg.ui.theme.screens.CultureRooms.ParkingScreen
import com.example.culturemotelg.ui.theme.screens.CultureRooms.SupperScreen
import com.example.culturemotelg.ui.theme.screens.CultureRooms.VVIPRoomsDetailScreen
import com.example.culturemotelg.ui.theme.screens.CustomerCare.CustomerCareScreen
import com.example.culturemotelg.ui.theme.screens.Dashboard.ClientScreen
import com.example.culturemotelg.ui.theme.screens.Dashboard.DashBoard
import com.example.culturemotelg.ui.theme.screens.Login.LoginScreen
import com.example.culturemotelg.ui.theme.screens.Signup.SignupScreen
import com.example.culturemotelg.ui.theme.screens.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_REGISTER) { SignupScreen(navController) }
        composable(ROUTE_SPLASH) {
            SplashScreen {
                navController.navigate(ROUTE_REGISTER) {
                    popUpTo(ROUTE_SPLASH) { inclusive = true }
                }
            }
        }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_HOME) { DashBoard(navController) }
        composable(ROUTE_ADD_CLIENT) { AddClientScreen(navController) }
        composable(ROUTE_INFO_CLIENT) { ViewClients(navController) } // Ensure this is the correct screen for ROUTE_INFO_CLIENT

        composable(ROUTE_VIEW_CLIENT) { ViewClients(navController) }
        composable(ROUTE_CULTUREROOMS) { CultureRoomsScreen(navController) }
        composable(ROUTE_VIP_ROOMS_DETAIL) { VIPRoomsDetailScreen(navController) }
        composable(ROUTE_VVIP_ROOMS_DETAIL) { VVIPRoomsDetailScreen(navController) }
        composable(ROUTE_MASTERCLASS_ROOMS_DETAIL) { MasterclassRoomsScreen(navController) }
        composable(ROUTE_BREAKFAST_SCREEN) { BreakfastScreen(navController) }
        composable(ROUTE_LUNCH_SCREEN) { LunchScreen(navController) }
        composable(ROUTE_SUPPER_SCREEN) { SupperScreen(navController) }
        composable(ROUTE_PARKING_SCREEN) { ParkingScreen(navController) }
        composable(ROUTE_USERDASHBOARD) { ClientScreen(navController) }

        composable(ROUTE_CONSTUMERCARE_SCREEN) { CustomerCareScreen(navController) }

        composable(ROUTE_PAY) { PaymentScreen(navController) }

        composable(ROUTE_FULL_SCREEN_IMAGE) { backStackEntry ->
            val imageRes = backStackEntry.arguments?.getInt("imageRes")
            imageRes?.let {
                FullscreenImageScreen(navController, it)
            }
        }

        composable("$ROUTE_UPDATE_CLIENT/{id}") { backStackEntry ->
            UpdateClientScreen(
                navController,
                backStackEntry.arguments?.getString("id")!!
            )
        }
    }
}
