package com.example.android_practice_2.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_practice_2.adapter.ClickListener
import com.example.android_practice_2.adapter.DailyAdapter
import com.example.android_practice_2.databinding.FragmentListBinding
import com.example.android_practice_2.model.Daily
import com.example.android_practice_2.viewmodel.MainViewModel

class ListFragment : Fragment(), ClickListener {

    private lateinit var cityName: String
    private lateinit var binding: FragmentListBinding
    private val args by navArgs<ListFragmentArgs>()
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchAPI(args.city)

        viewModel.dailyWeather.observe(viewLifecycleOwner, {
            if(it == null) {
                binding.tvNotFound.text = "CITY NOT FOUND"
            } else {
                binding.rvDaily.adapter = DailyAdapter(it.list, this)
                binding.toolbarTitle.text = it.city.name
                this.cityName = it.city.name.toString()
            }
        })

        binding.rvDaily.layoutManager = LinearLayoutManager(context)

        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun itemClick(item: Daily) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(item, cityName)
        findNavController().navigate(action)
    }
}