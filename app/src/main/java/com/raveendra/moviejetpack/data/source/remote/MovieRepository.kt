package com.raveendra.moviejetpack.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.qoolqas.moviedb.model.popular.PopularResultsItem
import com.raveendra.moviejetpack.data.Data
import com.raveendra.moviejetpack.model.tv.TvPopularItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieDataSource{
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource)
            }
    }
    override fun getMovie(): LiveData<List<Data>> {
        val result = MutableLiveData<List<Data>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovie(object : RemoteDataSource.LoadMovieCallback{
                override fun onMoviesReceived(movieResponse: List<PopularResultsItem>) {
                    val movieList = ArrayList<Data>()
                    for (response in movieResponse){
                        val movie = Data(
                            response.id,
                            response.title,
                            response.overview,
                            response.posterPath,
                            response.backdropPath
                        )
                        movieList.add(movie)
                    }
                    result.postValue(movieList)
                }

            })
        }

        return result
    }
    override fun getMovieDetail(movieId: Int): LiveData<Data> {
        val result = MutableLiveData<Data>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback{
                override fun onMovieDetailReceived(movieResponse: PopularResultsItem) {
                    val movie = Data(
                        movieResponse.id,
                        movieResponse.title,
                        movieResponse.overview,
                        movieResponse.posterPath,
                        movieResponse.backdropPath
                    )

                    result.postValue(movie)
                }
            })
        }

        return result
    }

    override fun getTvShow(): LiveData<List<Data>> {
        val result = MutableLiveData<List<Data>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTv(object : RemoteDataSource.LoadTvCallback{
                override fun onTvShowsReceived(tvShowResponse: List<TvPopularItem>) {
                    val tvShowList = ArrayList<Data>()
                    for (response in tvShowResponse){
                        val tvShow = Data(
                            response.id,
                            response.name,
                            response.overview,
                            response.posterPath,
                            response.backdropPath
                        )
                        tvShowList.add(tvShow)
                    }

                    result.postValue(tvShowList)
                }
            })
        }

        return result
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<Data> {
        val result = MutableLiveData<Data>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvDetail(tvShowId, object : RemoteDataSource.LoadTvDetailCallback{
                override fun onTvDetailReceived(tvShowResponse: TvPopularItem) {
                    val tvShow = Data(
                        tvShowResponse.id,
                        tvShowResponse.name,
                        tvShowResponse.overview,
                        tvShowResponse.posterPath,
                        tvShowResponse.backdropPath
                    )

                    result.postValue(tvShow)
                }
            })
        }

        return result
    }
}