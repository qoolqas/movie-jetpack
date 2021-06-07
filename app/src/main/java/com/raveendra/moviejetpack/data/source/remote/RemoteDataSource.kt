package com.raveendra.moviejetpack.data.source.remote

import com.qoolqas.moviedb.model.popular.PopularResultsItem
import com.raveendra.moviejetpack.data.source.connection.getApi
import com.raveendra.moviejetpack.model.tv.TvPopularItem
import com.raveendra.moviejetpack.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getMovie(
        callback: LoadMovieCallback
    ) {
        EspressoIdlingResource.increment()
        getApi.retrofitService.getPopular().await().results?.let { list ->
            callback.onMoviesReceived(
                list
            )
            EspressoIdlingResource.decrement()
        }
    }
    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        getApi.retrofitService.getDetails(movieId).await().let { list ->
            callback.onMovieDetailReceived(
                list
            )
            EspressoIdlingResource.decrement()
        }
    }
    suspend fun getTv(
        callback: LoadTvCallback
    ) {
        EspressoIdlingResource.increment()
        getApi.retrofitService.getTvPopular().await().results?.let { list ->
            callback.onTvShowsReceived(
                list
            )
            EspressoIdlingResource.decrement()
        }
    }
    suspend fun getTvDetail(movieId: Int, callback: LoadTvDetailCallback) {
        EspressoIdlingResource.increment()
        getApi.retrofitService.getDetailsTv(movieId).await().let { list ->
            callback.onTvDetailReceived(
                list
            )
            EspressoIdlingResource.decrement()
        }
    }
    interface LoadMovieCallback {
        fun onMoviesReceived(movieResponse: List<PopularResultsItem>)
    }
    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: PopularResultsItem)
    }

    interface LoadTvCallback {
        fun onTvShowsReceived(tvShowResponse: List<TvPopularItem>)
    }

    interface LoadTvDetailCallback {
        fun onTvDetailReceived(tvShowResponse: TvPopularItem)
    }
}