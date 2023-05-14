package com.example.newsapplication.Navigation


sealed class Screens(val route: String){

    object MainScreen: Screens("main")

    object FullArticlesScreen: Screens("FullArticlesScreen")

    object FavouriteScreen: Screens("FavouriteScreen")

}