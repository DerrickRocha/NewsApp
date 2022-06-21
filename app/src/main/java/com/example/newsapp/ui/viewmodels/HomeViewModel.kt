package com.example.newsapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.NewsAppDependencies
import com.example.newsapp.models.NewsSource
import com.example.newsapp.models.PoliticalBias
import com.example.newsapp.repositories.NewsSourceRepository
import com.example.newsapp.rss.RssRetriever
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: NewsSourceRepository = NewsAppDependencies.newsSourceRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val biases = PoliticalBias.values()
    val sources: LiveData<List<NewsSource>> get() = _sources
    private val _sources = MutableLiveData<List<NewsSource>>()

    fun loadNewsSources(index: Int) {
        if (index >= biases.size) {
            Log.e("Index out of bounds", "Index exceeds bias list")
            return
        }
        viewModelScope.launch(dispatcher) {
            _sources.postValue(repository.getNewsSources(biases[index]))
        }
    }

    fun onCardClicked(source: NewsSource) {

    }
}