package com.example.newsapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.NewsSource
import com.example.newsapp.models.PoliticalBias
import com.example.newsapp.repositories.NewsSourceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: NewsSourceRepository
): ViewModel() {

    private val biases = PoliticalBias.values()
    private val _sources = MutableLiveData<List<NewsSource>>()

    fun loadNewsSources(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (index >= biases.size) {
                Log.e("Index out of bounds", "Index exceeds bias list")
                return@launch
            }
            _sources.value = repository.getNewsSources(biases[index])
        }
    }
}