package com.example.newsapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.NewsAppDependencies
import com.example.newsapp.rss.RssRetriever
import com.example.newsapp.utils.SingleLiveEvent
import com.prof.rssparser.Channel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsSourceScreenViewModel(
    private val retriever: RssRetriever = NewsAppDependencies.retriever,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val channel: LiveData<Channel> get() = _channel
    private val _channel = SingleLiveEvent<Channel>()

    fun loadRss(url: String) {
        viewModelScope.launch(dispatcher) {
            _channel.postValue(retriever.getFeed(url))
        }
    }
}