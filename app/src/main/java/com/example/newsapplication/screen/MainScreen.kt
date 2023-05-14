package com.example.newsapplication.screen


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newsapplication.TopBar


@Composable
fun MainScreen (
    navController: NavController
) {
    TopBar(navController)
}