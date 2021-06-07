package com.raveendra.moviejetpack.data.source.connection

import com.qoolqas.moviedb.model.popular.PopularMovieResponse
import com.qoolqas.moviedb.model.popular.PopularResultsItem
import com.raveendra.moviejetpack.BuildConfig
import com.raveendra.moviejetpack.model.tv.TvPopularItem
import com.raveendra.moviejetpack.model.tv.TvPopularResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") api: String = BuildConfig.API_KEY
    ): Call<PopularMovieResponse>


    @GET("tv/popular")
    fun getTvPopular(
        @Query("api_key") api: String = BuildConfig.API_KEY
    ): Call<TvPopularResponse>

    //region Details
    @GET("movie/{movie_id}")
    fun getDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") api: String = BuildConfig.API_KEY

    ): Call<PopularResultsItem>
    @GET("tv/{tv_id}")
    fun getDetailsTv(
        @Path("tv_id") id: Int,
        @Query("api_key") api: String = BuildConfig.API_KEY

    ): Call<TvPopularItem>
    //endregion
}