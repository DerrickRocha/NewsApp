package com.example.newsapp.repositories

import com.example.newsapp.models.NewsSource
import com.example.newsapp.models.PoliticalBias
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NewsSourceRepository {
    suspend fun getNewsSources(bias: PoliticalBias): List<NewsSource>
}

class RealNewsSourceRepository(
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
): NewsSourceRepository {
    override suspend fun getNewsSources(bias: PoliticalBias): List<NewsSource> {
        return withContext(dispatcher) {
            NewsSource.values().filter { newsSource -> newsSource.politicalBias == bias }
        }
    }
}