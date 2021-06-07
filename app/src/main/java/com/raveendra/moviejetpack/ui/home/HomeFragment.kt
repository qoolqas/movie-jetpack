package com.raveendra.moviejetpack.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raveendra.moviejetpack.databinding.FragmentHomeBinding
import com.raveendra.moviejetpack.ui.ViewModelFactory
import com.raveendra.moviejetpack.utils.gone
import com.raveendra.moviejetpack.utils.visible

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = ViewModelFactory.getInstance()
        activity?.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[MovieViewModel::class.java]
        }


        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.moviePb.visibility = visible
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val courses = viewModel.getMovie()
//        val academyAdapter = MovieAdapter()
//        academyAdapter.setMovie(courses)
//        with(binding.movieRv) {
//            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
//            adapter = academyAdapter
//            binding.moviePb.visibility = gone
//        }
        viewModel.getMovie().observe(viewLifecycleOwner, Observer { listMovie ->
            val academyAdapter = MovieAdapter()
            academyAdapter.setMovie(listMovie)
            with(binding.movieRv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
                binding.moviePb.visibility = gone
            }
        })
    }
}