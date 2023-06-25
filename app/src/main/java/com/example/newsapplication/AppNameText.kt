package com.example.newsapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplication.ui.theme.*

@Composable
fun DarkModeToggle(
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    Row(
        modifier = Modifier.background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Light Mode",
            color =  textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable { onToggleDarkMode(false) }
                .padding(end = 250.dp)
        )

        Text(
            text = "Dark Mode",
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable { onToggleDarkMode(true) }
        )
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun AppNameText(
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    Text(
        text = "News App",
        fontSize = 30.sp,
        style = TextStyle(
            color = textColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
            .background(backgroundColor)
    )

    DarkModeToggle(isDarkMode = isDarkMode, onToggleDarkMode = onToggleDarkMode)
}