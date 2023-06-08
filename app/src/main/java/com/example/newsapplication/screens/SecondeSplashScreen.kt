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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapplication.navigation.Graph
import com.example.newsapplication.ui.theme.*


@OptIn(ExperimentalTextApi::class)
@Composable
fun SecondSplashScreen(navController: NavController){

    Column (modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        //For LottieAnimation

        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("//https://assets2.lottiefiles.com/packages/lf20_2LdLki.json"))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever )

        Spacer(modifier = Modifier.padding(3.dp))

        //For Text
        val textColor = listOf(customRealRed,customRed, customOrange, customYellow, customGreen, customBlue, customBlur)
        Text(text = buildAnnotatedString {
            append("You have come to the ")
            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        colors = textColor
                        )
                    )
                ){
                    append("right place to learn ")
                }
                append("the latest and accurate news. Come in.")

        })



        Spacer(modifier = Modifier.padding(5.dp))


        //For button
        Button(onClick = {
            navController.popBackStack()
            navController.navigate(Graph.HOME)
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = custemPink),
            shape = CutCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 30.dp,
                pressedElevation = 25.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier
                .size(width = 150.dp, height = 35.dp)
        ) {
            Text(text = "Continue",
                color = Color.White)
        }

    }

}


