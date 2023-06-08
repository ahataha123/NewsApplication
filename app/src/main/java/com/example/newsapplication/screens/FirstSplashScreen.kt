package com.example.newsapplication.screens


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapplication.navigation.AuthScreen
import kotlinx.coroutines.delay


@Composable
fun FirstSplashScreen(navController: NavController){

    LaunchedEffect(key1 = true){
        //For after 4 second continues secondScreen
        delay(4000)
        navController.popBackStack()
        navController.navigate(AuthScreen.Second.route)
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 60.dp)){

        //For LottieAnimation
       val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://assets4.lottiefiles.com/private_files/lf30_cbizhsdy.json"))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever )

    }

}


