package com.example.newsapplication.screen


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newsapplication.SimpleAppBar


@Composable
fun FavouriteScreen (navController: NavController) {
    SimpleAppBar(title = "Saved Articles", navController = navController)
}