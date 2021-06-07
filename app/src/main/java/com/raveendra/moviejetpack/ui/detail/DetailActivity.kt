package com.raveendra.moviejetpack.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.raveendra.moviejetpack.R
import com.raveendra.moviejetpack.databinding.ActivityDetailBinding
import com.raveendra.moviejetpack.data.Data
import com.raveendra.moviejetpack.ui.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    var id: String = ""
    private var code: String = ""
    private var appBarExpanded = true
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
    val factory = ViewModelFactory.getInstance()
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this,factory).get(DetailViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getStringExtra(EXTRA_ID)!!
        code = intent.getStringExtra(EXTRA_TYPE)!!

        binding.collapsingToolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, R.color.redDark)
        )
        binding.collapsingToolbar.setExpandedTitleColor(
            ContextCompat.getColor(this, R.color.transparent)
        )
        setSupportActionBar(binding.toolbar1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (kotlin.math.abs(verticalOffset) > 200) {
                appBarExpanded = false
                invalidateOptionsMenu()
            } else {
                appBarExpanded = true
                invalidateOptionsMenu()
            }
        })

        if (code == "movie"){
            getMovie()
        }else{
            getTv()
        }
    }


    private fun getMovie() {
        viewModel.getMovieDetail(id.toInt()).observe(this, Observer {
            populateCourse(it)
        })
//        viewModel.setMovie(id)
//        populateCourse(viewModel.getDetailMovieById())
//        val listMovie = ArrayList<Data>()
//        for (course in listMovie) {
//            if (course.id!!.equals(id)) {
//                populateCourse(course)
//            }
//        }
    }

    private fun getTv() {
        viewModel.getTvShowDetail(id.toInt()).observe(this, Observer {
            populateCourse(it)
        })
//        viewModel.setTv(id)
//        populateCourse(viewModel.getDetailTvById())
//        val listTv = ArrayList<Data>()
//        for (course in listTv) {
//            if (course.id!!.equals(id)) {
//                populateCourse(course)
//            }
//        }
    }
    private fun populateCourse(courseEntity: Data) {
        binding.detailTitle.text = courseEntity.name
        binding.detailDescription.text = courseEntity.desc
        binding.collapsingToolbar.title = courseEntity.name

        Glide.with(this@DetailActivity)
            .load("https://image.tmdb.org/t/p/w185" + courseEntity.poster)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(binding.detailPoster)
        Glide.with(this@DetailActivity)
            .load("https://image.tmdb.org/t/p/w185" + courseEntity.img_preview)
            .into(binding.detailBackdrop)

    }
}