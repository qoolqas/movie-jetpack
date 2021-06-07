package com.raveendra.moviejetpack.ui.home

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest{
    private lateinit var viewModel: MovieViewModel
    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }
    @Test
    fun getMovie() {
        val courseEntities = viewModel.getMovie()
        assertNotNull(courseEntities)
        assertEquals(10, courseEntities.size)
    }
}