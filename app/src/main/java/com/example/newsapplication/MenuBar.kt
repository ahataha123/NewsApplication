package com.example.newsapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapplication.Navigation.Screens



@Composable
fun TopBar(navController: NavController) {
    var menuExpanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = "NewsApp") },
        actions = {
            Row {
                IconButton(onClick = { menuExpanded = !menuExpanded }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Favorites")
                }
                Spacer(modifier = Modifier.width(8.dp))
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                ) {
                    DropdownMenuItem(onClick = { menuExpanded = true; navController.navigate(Screens.FavouriteScreen.route)}) {
                        Icon(Icons.Filled.Favorite,contentDescription = null)
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Favorites")
                    }
                }
            }
        }
    )
}


@Composable
fun SimpleAppBar(title: String, navController: NavController) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "arrowBack")
            }
        },
        title = { Text(text = title) },
        modifier = Modifier.fillMaxWidth()
    )
}

