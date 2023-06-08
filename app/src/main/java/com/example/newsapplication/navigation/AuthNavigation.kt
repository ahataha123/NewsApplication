package com.example.newsapplication.navigation



import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newsapplication.screens.FirstSplashScreen
import com.example.newsapplication.screens.SecondSplashScreen


fun NavGraphBuilder.authNavGraph(navController : NavController){
        navigation(
            route = Graph.AUTHENTICATION,
            startDestination = AuthScreen.First.route
        ){
            composable(route = AuthScreen.First.route) {
                FirstSplashScreen(navController)

            }
            composable(route = AuthScreen.Second.route ){
                SecondSplashScreen(navController = navController)
            }
        }
}




sealed class AuthScreen (val route: String){
    object First : AuthScreen(route = "FIRS")
    object Second : AuthScreen(route = "SECOND")
}