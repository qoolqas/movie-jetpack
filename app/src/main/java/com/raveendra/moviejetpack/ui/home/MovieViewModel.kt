package com.raveendra.moviejetpack.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raveendra.moviejetpack.data.Data
import com.raveendra.moviejetpack.data.source.remote.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovie(): LiveData<List<Data>> = movieRepository.getMovie()
//    fun getMovie(): List<Data> = DataDummy.generateDataMovieDummy()
}