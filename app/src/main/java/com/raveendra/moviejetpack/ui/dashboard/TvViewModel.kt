package com.raveendra.moviejetpack.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raveendra.moviejetpack.data.Data
import com.raveendra.moviejetpack.data.source.remote.MovieRepository

class TvViewModel(private val movieRepository: MovieRepository)  : ViewModel() {
    fun getTv(): LiveData<List<Data>> = movieRepository.getTvShow()
//    fun getTv(): List<Data> = DataDummy.generateDataTvShowDummy()
}