package com.raveendra.moviejetpack.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.raveendra.moviejetpack.databinding.FragmentDashboardBinding
import com.raveendra.moviejetpack.ui.ViewModelFactory
import com.raveendra.moviejetpack.ui.home.MovieViewModel
import com.raveendra.moviejetpack.utils.visible

class TvFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: TvViewModel

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
            )[TvViewModel::class.java]
        }
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        binding.tvPb.visibility = visible
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTv().observe(viewLifecycleOwner, Observer { listMovie ->
            val academyAdapter = TvAdapter()
            academyAdapter.setTv(listMovie)
            with(binding.tvRv) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
                binding.tvPb.visibility = com.raveendra.moviejetpack.utils.gone
            }
        })
//        val courses = viewModel.getTv()
//        val academyAdapter = TvAdapter()
//        academyAdapter.setTv(courses)
//        with(binding.tvRv) {
//            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
//            setHasFixedSize(true)
//            adapter = academyAdapter
//            binding.tvPb.visibility = gone
//        }
    }
}