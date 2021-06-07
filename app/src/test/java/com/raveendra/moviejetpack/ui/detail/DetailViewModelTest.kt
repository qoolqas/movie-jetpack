package com.raveendra.moviejetpack.ui.detail

import com.raveendra.moviejetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest{
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDataMovieDummy()[0]
    private val dummyTv = DataDummy.generateDataTvShowDummy()[0]
    private val movieId = dummyMovie.id
    private val tvId = dummyTv.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setMovie(movieId)
        viewModel.setTv(tvId)
    }
    @Test
    fun getDetailMovie() {
        val movie = viewModel.getDetailMovieById()
        assertNotNull(movie)
        assertEquals(dummyMovie.name, movie.name)
        assertEquals(dummyMovie.desc, movie.desc)
        assertEquals(dummyMovie.poster, movie.poster)
        assertEquals(dummyMovie.img_preview, movie.img_preview)
        assertEquals(dummyMovie.genre, movie.genre)
    }
    @Test
    fun getDetailTv() {
        val tv = viewModel.getDetailTvById()
        assertNotNull(tv)
        assertEquals(dummyTv.name, tv.name)
        assertEquals(dummyTv.desc, tv.desc)
        assertEquals(dummyTv.poster, tv.poster)
        assertEquals(dummyTv.img_preview, tv.img_preview)
        assertEquals(dummyTv.genre, tv.genre)
    }
}