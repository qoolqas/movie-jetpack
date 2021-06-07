package com.raveendra.moviejetpack.data.source.remote

import androidx.lifecycle.LiveData
import com.raveendra.moviejetpack.data.Data

interface MovieDataSource {

    fun getMovie(): LiveData<List<Data>>

    fun getMovieDetail(id: Int): LiveData<Data>

    fun getTvShow(): LiveData<List<Data>>

    fun getTvShowDetail(id: Int): LiveData<Data>
}