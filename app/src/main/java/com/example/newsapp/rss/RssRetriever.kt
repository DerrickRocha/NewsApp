package com.example.newsapp.rss

import com.prof.rssparser.Channel
import com.prof.rssparser.Parser

interface RssRetriever {
    suspend fun getFeed(feedUrl: String): Channel
}

class RealRssRetriever(private val parser: Parser) : RssRetriever {
    override suspend fun getFeed(feedUrl: String): Channel = parser.getChannel(feedUrl)
}