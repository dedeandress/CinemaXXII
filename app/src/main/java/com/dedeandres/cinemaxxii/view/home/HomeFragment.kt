package com.dedeandres.cinemaxxii.view

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.dedeandres.cinemaxxii.R
import com.dedeandres.cinemaxxii.di.DaggerFeatureComponent
import com.dedeandres.cinemaxxii.di.util.CoreInjectHelper
import kotlinx.android.synthetic.main.layout_header_home.*
import timber.log.Timber
import javax.inject.Inject

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
