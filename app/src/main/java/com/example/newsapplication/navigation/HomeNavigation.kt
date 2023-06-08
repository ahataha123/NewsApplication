package com.example.newsapplication.navigation


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapplication.AppNameText
import com.example.newsapplication.screens.NewsList
import com.example.newsapplication.screens.NewsSearchScreen
import com.example.newsapplication.screens.DetailView
import com.example.newsapplication.screens.savedNewsView

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarNavigation.Home.route
    ) {
        composable(route = BottomBarNavigation.Home.route) {
            Column(modifier = Modifier.fillMaxSize()){
                AppNameText()
                NewsList(navController = navController)
            }


        }
        composable(route = BottomBarNavigation.SEARCH.route) {
            Column(modifier = Modifier.fillMaxSize()){
                AppNameText()
                NewsSearchScreen(navController = navController)
            }


        }
        composable(route = BottomBarNavigation.SAVE.route) {
            Column(modifier = Modifier.fillMaxSize()){
                AppNameText()
                savedNewsView(navController = navController)
            }

        }
        detailsNavGraph(navController =navController)
    }
}
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
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


sealed class HomeScreen(val route: String){
    object Home : HomeScreen(route = "HOME")

}
