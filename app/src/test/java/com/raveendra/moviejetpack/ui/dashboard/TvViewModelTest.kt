package com.raveendra.moviejetpack.ui.dashboard

import com.raveendra.moviejetpack.ui.home.MovieViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvViewModelTest{
    private lateinit var viewModel: TvViewModel
    @Before
    fun setUp() {
        viewModel = TvViewModel()
    }
    @Test
    fun getTv() {
        val courseEntities = viewModel.getTv()
        assertNotNull(courseEntities)
        assertEquals(10, courseEntities.size)
    }
}