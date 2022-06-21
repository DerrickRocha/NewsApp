package com.example.newsapp

import android.app.Application
import com.example.newsapp.repositories.NewsSourceRepository
import com.example.newsapp.repositories.RealNewsSourceRepository
import com.example.newsapp.rss.RealRssRetriever
import com.example.newsapp.rss.RssRetriever
import com.prof.rssparser.Parser
import java.nio.charset.Charset

internal object NewsAppDependencies {

    lateinit var newsSourceRepository: NewsSourceRepository
        private set

    lateinit var retriever: RssRetriever
        private set

    fun initialize(application: Application) {
        newsSourceRepository = RealNewsSourceRepository()
        retriever = RealRssRetriever(
            Parser.Builder()
                .context(application)
                .charset(Charset.forName("ISO-8859-7"))
               // .cacheExpirationMillis(24L * 60L * 60L * 1000L) // one day
                .build()
        )
    }
}