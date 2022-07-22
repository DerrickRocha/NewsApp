package com.example.newsapp

import com.example.newsapp.repositories.NewsSourceRepository
import com.example.newsapp.repositories.RealNewsSourceRepository
import com.example.newsapp.repositories.RealSliderRepository
import com.example.newsapp.repositories.SliderRepository
import com.example.newsapp.rss.RealRssRetriever
import com.example.newsapp.rss.RssRetriever
import com.prof.rssparser.Parser
import java.nio.charset.Charset

internal object NewsAppDependencies {

    lateinit var newsSourceRepository: NewsSourceRepository
        private set

    lateinit var sliderRepository: SliderRepository
        private set

    lateinit var retriever: RssRetriever
        private set

    lateinit var newsApplication: NewsAppApplication
    private set

    fun initialize(application: NewsAppApplication) {
        newsApplication = application
        newsSourceRepository = RealNewsSourceRepository()
        sliderRepository = RealSliderRepository()
        retriever = RealRssRetriever(
            Parser.Builder()
                .context(application)
                .charset(Charset.forName("ISO-8859-7"))
                .cacheExpirationMillis(60L * 1000L)
                .build()
        )
    }
}