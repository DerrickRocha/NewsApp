package com.example.newsapp.repositories

import com.example.newsapp.models.NewsSource
import com.example.newsapp.models.PoliticalBias

interface NewsSourceRepository {
    suspend fun getNewsSources(bias: PoliticalBias): List<NewsSource>
}

class RealNewsSourceRepository(): NewsSourceRepository {
    override suspend fun getNewsSources(bias: PoliticalBias): List<NewsSource> = NewsSource.values().filter { newsSource -> newsSource.politicalBias == bias }
}