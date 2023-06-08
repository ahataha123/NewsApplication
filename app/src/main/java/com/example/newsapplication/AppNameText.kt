package com.example.newsapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplication.ui.theme.*

@SuppressLint("SuspiciousIndentation")
@Composable
fun AppNameText() {
    val isDarkTheme = isSystemInDarkTheme()

    Text(
        text = "News App",
        fontSize = 30.sp,
        style = TextStyle(
            color = if (isDarkTheme) Color.White else Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
            .padding(15.dp)
            .background(if (isDarkTheme) Color.Black else Color.White) // Optional: Add a background color for contrast
    )
}
