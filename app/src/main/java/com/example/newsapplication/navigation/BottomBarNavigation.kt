package com.example.newsapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavigation(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarNavigation(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )

    object SEARCH : BottomBarNavigation(
        route = "SEARCH",
        title = "SEARCH",
        icon = Icons.Default.Search
    )

    object SAVE : BottomBarNavigation(
        route = "SAVE",
        title = "SAVE",
        icon = Icons.Default.ShoppingCart
    )
}