package com.example.newsapp

import android.app.Application

class NewsAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        NewsAppDependencies.initialize()
    }
}