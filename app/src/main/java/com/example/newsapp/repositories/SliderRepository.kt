package com.example.newsapp.repositories

import android.content.Context
import android.content.SharedPreferences
import com.example.newsapp.NewsAppApplication
import com.example.newsapp.NewsAppDependencies
import com.example.newsapp.R

interface SliderRepository {
    fun getStoredSliderPosition(): Int
    fun saveSliderPosition(sliderPosition: Int)
}

class RealSliderRepository(
    application: NewsAppApplication = NewsAppDependencies.newsApplication,
    private val preferences: SharedPreferences = application.getSharedPreferences(
        application.getString(R.string.slider_prefs),
        Context.MODE_PRIVATE
    ),
) : SliderRepository {
    override fun getStoredSliderPosition(): Int {
        return preferences.getInt(SLIDER_POSITION_KEY, 0)
    }

    override fun saveSliderPosition(sliderPosition: Int) {
        with(preferences.edit()) {
            putInt(SLIDER_POSITION_KEY, sliderPosition)
            commit()
        }
    }

    companion object {
        private const val SLIDER_POSITION_KEY = "sliderPositionKey"
    }
}