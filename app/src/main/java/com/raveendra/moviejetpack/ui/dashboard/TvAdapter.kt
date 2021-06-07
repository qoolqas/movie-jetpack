package com.raveendra.moviejetpack.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raveendra.moviejetpack.R
import com.raveendra.moviejetpack.databinding.ItemCardDiscoverBinding
import com.raveendra.moviejetpack.data.Data
import com.raveendra.moviejetpack.ui.detail.DetailActivity
import com.raveendra.moviejetpack.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.raveendra.moviejetpack.ui.detail.DetailActivity.Companion.EXTRA_TYPE

class TvAdapter: RecyclerView.Adapter<TvAdapter.CourseViewHolder>() {
    private var listCourses = ArrayList<Data>()

    fun setTv(courses: List<Data>?) {
        if (courses == null) return
        this.listCourses.clear()
        this.listCourses.addAll(courses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemsAcademyBinding = ItemCardDiscoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = listCourses[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listCourses.size


    class CourseViewHolder(private val binding: ItemCardDiscoverBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Data) {
            with(binding) {
                movieTitle.text = course.name
                movieOverview.text = course.desc
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_ID, course.id.toString())
                    intent.putExtra(EXTRA_TYPE, "tv")
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w185" +course.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(moviePoster)
            }
        }
    }
}