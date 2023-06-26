package com.example.newsapplication.screens


import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapplication.AppNameText
import com.example.newsapplication.viewmodel.OneNewsViewModel
import com.example.newsapplication.viewmodel.SavedNewsViewModel
import com.example.newsapplication.model.NewsModel
import com.example.newsapplication.ui.theme.Teal200
import com.example.newsapplication.utilities.Resource


@Composable
fun DetailView(content: String,
               viewModel : OneNewsViewModel = hiltViewModel(),
               saveViewModel: SavedNewsViewModel = hiltViewModel()
               ){
    val oneNews = produceState<Resource<NewsModel>>(initialValue = Resource.Loading()){
        value = viewModel.getOneNews(content)
    }.value
    when(oneNews){
        is Resource.Success ->{
            if (oneNews.data!!.articles.isNotEmpty()){
                Column(modifier = Modifier.fillMaxSize()) {

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)){
                        //For we can see website in app directly
                        AndroidView(factory = {
                            WebView(it).apply {
                                layoutParams = ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                webViewClient = WebViewClient()
                                loadUrl(oneNews.data!!.articles[0].url)
                            }
                        }, update = {
                            it.loadUrl(oneNews.data!!.articles[0].url)
                        })
                        Row(modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(5.dp)) {
                            val context = LocalContext.current
                            CustomFloatingButton {
                                Toast.makeText(context,"Successfully saved!",Toast.LENGTH_LONG).show()
                                val saveNews= oneNews.data!!.articles[0]
                                saveViewModel.saveNews(saveNews.author ?:"",saveNews.content?:"",saveNews.description?:"",saveNews.publishedAt?:"",saveNews.title?:"",saveNews.url?:"",saveNews.urlToImage?:"")
                            }
                        }

                    }
                }
            }else{

            }
        }
        is Resource.Error ->{
            Text(text = oneNews.message!!)
            println("Resource Error")
        }else->{
        println("error")
    }
    }
}

@Composable
fun CustomFloatingButton(onSave: () -> Unit) {
    // Remember whether the button is clicked or not
    val isClicked = remember { mutableStateOf(false) }

    FloatingActionButton(
        onClick = {
            isClicked.value = !isClicked.value // Toggle the clicked state
            onSave()
        },
        backgroundColor = if (isClicked.value) Teal200 else Color.White,
        contentColor = if (isClicked.value) Color.White else Teal200
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Add Button",
            tint = if (isClicked.value) Color.White else Teal200
        )
    }
}




