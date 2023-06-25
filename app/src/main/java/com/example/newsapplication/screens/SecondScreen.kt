package com.example.newsapplication.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapplication.navigation.Graph




@Composable
fun SecondSplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // For LottieAnimation`
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("//https://assets2.lottiefiles.com/packages/lf20_2LdLki.json"))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)

        Spacer(modifier = Modifier.padding(8.dp))

        // For Text
        Text(
            text = "Welcome to NewsApp",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        // For button
        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate(Graph.HOME)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            shape = CutCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 30.dp,
                pressedElevation = 25.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier.size(width = 150.dp, height = 35.dp)
        ) {
            Text(text = "Continue", color = Color.White)
        }
    }
}


