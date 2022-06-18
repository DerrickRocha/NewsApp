package com.example.newsapp

import com.example.newsapp.repositories.NewsSourceRepository
import com.example.newsapp.repositories.RealNewsSourceRepository

internal object NewsAppDependencies {

    lateinit var newsSourceRepository: NewsSourceRepository
        private set

    fun initialize() {
        newsSourceRepository = RealNewsSourceRepository()
    }
}