package com.example.newsapp.ui.viewmodels

import android.util.Log
import com.example.newsapp.models.NewsSource
import com.example.newsapp.repositories.NewsSourceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Test
import org.mockito.Mockito.*

class HomeViewModelTest {

    private val repository = mock(NewsSourceRepository::class.java)
    private val sources = listOf(NewsSource.CBS)

    @ExperimentalCoroutinesApi
    @Test
    fun loadNewsSources_WhenIndexOutOfBounds_confirmErrorIsLogged() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val viewModel = HomeViewModel(repository, dispatcher)
        mockStatic(Log::class.java).use { mockedLog ->
            viewModel.loadNewsSources(99)
            mockedLog.verify { Log.e("Index out of bounds", "Index exceeds bias list") }
        }
    }
}