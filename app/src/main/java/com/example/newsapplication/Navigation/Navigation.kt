package com.example.newsapplication.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapplication.screen.FavouriteScreen
import com.example.newsapplication.screen.FullArticlesScreen
import com.example.newsapplication.screen.MainScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(Screens.FullArticlesScreen.route) {
            FullArticlesScreen(navController = navController)
        }

        composable(Screens.FavouriteScreen.route) {
            FavouriteScreen(navController = navController)
        }

}
 }

