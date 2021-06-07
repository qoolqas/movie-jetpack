package com.raveendra.moviejetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raveendra.moviejetpack.data.Data
import com.raveendra.moviejetpack.data.source.remote.MovieRepository

class DetailViewModel(private val movieRepository: MovieRepository) :ViewModel() {
    fun getMovieDetail(movieId: Int): LiveData<Data> =
        movieRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<Data> =
        movieRepository.getTvShowDetail(tvShowId)
//    private lateinit var movieId: String
//    private lateinit var tvShowId: String
//
//    fun setMovie(courseId: String) {
//        this.movieId = courseId
//    }
//    fun setTv(courseId: String) {
//        this.tvShowId = courseId
//    }
//
//    fun getDetailMovieById(): Data {
//        lateinit var course: Data
//        val listMovie = ArrayList<Data>()
//        for (movie in listMovie){
//            if (movie.id!!.equals(movieId)){
//                course = movie
//            }
//        }
//        return course
//    }
//    fun getDetailTvById(): Data {
//        lateinit var course: Data
//        val listTv = ArrayList<Data>()
//        for (tv in listTv){
//            if (tv.id!!.equals(tvShowId)){
//                course = tv
//            }
//        }
//        return course
//    }
}