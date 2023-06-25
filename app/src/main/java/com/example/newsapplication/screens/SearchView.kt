package com.example.newsapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapplication.viewmodel.SearchViewModel

@Composable
fun NewsSearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel(),
                     isDarkMode: Boolean,
                     onToggleDarkMode: (Boolean) -> Unit) {
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxWidth()
                .background(backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            SearchBar(
                hint = "Search news...",
                modifier = Modifier.padding(16.dp),
                onSearch = { query ->
                    viewModel.loadSearchedNews(query)
                },
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )

            Spacer(modifier = Modifier.height(10.dp))

            SearchView(navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode)
        }
    }
}



@Composable
fun SearchView(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val searchedNewsList by remember { viewModel.searchedNewsList }
    val errorMessage by remember { viewModel.errorMessage }
    val isLoading by remember { viewModel.isLoading }

    NewsListView(
        articles = searchedNewsList,
        navController = navController,
        isDarkMode = isDarkMode,
        onToggleDarkMode = onToggleDarkMode
    )

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.Red)
        }
        if (errorMessage.isNotEmpty()) {
            println(errorMessage)
        }
    }
}



@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }
    val backgroundColor = if (isDarkMode) Color.White else Color.White
    val textColor = if (isDarkMode) Color.Black else Color.White
    Box(modifier = modifier
        .background(backgroundColor)) {
        BasicTextField(
            value = text,
            onValueChange = {
                if (it.isNotEmpty()) {
                    text = it
                    onSearch(it)
                } else {
                    text = ""
                    println("empty")
                }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle.Default,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .shadow(7.dp)
                .background(backgroundColor)
                .padding(horizontal = 30.dp, vertical = 15.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = textColor,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
