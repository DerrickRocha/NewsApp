package com.example.newsapp.rss

import com.prof.rssparser.Channel
import com.prof.rssparser.Parser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RssRetriever {
    suspend fun getFeed(feedUrl: String): Channel
}

class RealRssRetriever(
    private val parser: Parser,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RssRetriever {
    override suspend fun getFeed(feedUrl: String): Channel {
        return withContext(dispatcher) {
            parser.getChannel(feedUrl)
        }
    }

}