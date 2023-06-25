package com.example.newsapplication.navigation


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapplication.AppNameText
import com.example.newsapplication.screens.*

@Composable
fun HomeNavGraph(navController: NavHostController) {
    var isDarkMode by remember { mutableStateOf(false) }
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarNavigation.Home.route
    ) {
        composable(route = BottomBarNavigation.Home.route) {
            Column(modifier = Modifier.fillMaxSize()){
                AppNameText(isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = it })
                SwipeRefreshCompose(navController = navController,isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = it })
                NewsList(navController = navController,isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = it })

            }


        }
        composable(route = BottomBarNavigation.SEARCH.route) {
            Column(modifier = Modifier.fillMaxSize()){
                AppNameText(isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = it })
                NewsSearchScreen(navController = navController,isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = it })
            }


        }
        composable(route = BottomBarNavigation.SAVE.route) {
            Column(modifier = Modifier.fillMaxSize()){
                AppNameText(isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = it })
                SavedNewsView(navController = navController)
            }

        }
        detailsNavGraph()
    }
}

fun NavGraphBuilder.detailsNavGraph() {
    navigation(
        route = Graph.DETAILS,
        startDestination = "details_graph/{title}"
    ) {
        composable(route = "details_graph/{title}", arguments = listOf(
            navArgument("title"){
                type = NavType.StringType

            }
        )){


            val title = remember {
                it.arguments?.getString("title")
            }
            println(title)
            DetailView(

                content = title ?:"",
            )
        }

    }

}



