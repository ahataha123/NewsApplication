package com.example.newsapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavigation(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HOME : BottomBarNavigation(
        route = "HOME",
        title = "Home",
        icon = Icons.Default.Home
    )

    object SEARCH : BottomBarNavigation(
        route = "SEARCH",
        title = "Search",
        icon = Icons.Default.Search
    )

    object SAVE : BottomBarNavigation(
        route = "SAVE",
        title = "Favourites",
        icon = Icons.Default.Favorite
    )
}