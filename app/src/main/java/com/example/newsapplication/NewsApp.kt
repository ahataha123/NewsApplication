package com.example.newsapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//if you use hilt, you have to add this
@HiltAndroidApp
class NewsApp : Application()