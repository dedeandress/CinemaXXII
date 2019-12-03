package com.dedeandres.cinemaxxii.view.home

import android.app.Dialog
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.lifecycle.ViewModelProvider

import com.dedeandres.cinemaxxii.R
import com.dedeandres.cinemaxxii.di.DaggerFeatureComponent
import com.dedeandres.cinemaxxii.di.util.CoreInjectHelper
import com.dedeandres.cinemaxxii.util.Resource
import com.dedeandres.cinemaxxii.util.ResourceState
import com.dedeandres.cinemaxxii.view.home.entity.MovieResult
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_header_home.*
import timber.log.Timber
import javax.inject.Inject
import com.dedeandres.cinemaxxii.util.SafeObserver

class HomeFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFeatureComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireActivity().applicationContext))
            .build()
            .inject(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            iv_search.id -> {
                val searchQuery = et_search.text.toString()
                viewModel.search(searchQuery)
                viewModel.movieLivedata.observe(viewLifecycleOwner, SafeObserver(this::handleSearchMovie))
            }
        }
    }

    private fun handleSearchMovie(movie: Resource<List<MovieResult>>) {
        when(movie.state) {
            ResourceState.LOADING -> {
                Timber.e("Resource state Loading")
                progress_bar.visibility = View.VISIBLE
            }
            ResourceState.SUCCESS -> {
                Timber.e("Resource state Success")
                progress_bar.visibility = View.GONE
                test.text = movie.data.toString()
            }
            ResourceState.ERROR -> {
                Timber.e("Resource state Error")
                progress_bar.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        iv_search.setOnClickListener(this)
    }

}
