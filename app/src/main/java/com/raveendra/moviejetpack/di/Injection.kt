package com.raveendra.moviejetpack.di

import com.raveendra.moviejetpack.data.source.remote.MovieRepository
import com.raveendra.moviejetpack.data.source.remote.RemoteDataSource

object Injection {

    fun provideCatalogRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource)
    }
}