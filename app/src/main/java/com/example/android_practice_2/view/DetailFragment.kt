package com.example.android_practice_2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.android_practice_2.databinding.FragmentDetailBinding
import com.example.android_practice_2.viewmodel.MainViewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val daily = args.daily
        val cityName = args.cityName

        binding.toolbarTitle.text = cityName
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        val mainTemp = daily.main.temp.toString().substringBefore(".")
        val feelsLike = daily.main.feelsLike.toString().substringBefore(".")

        binding.tvMainTemp.text = mainTemp
        binding.tvFeelsLike.text = "Feels Like: $feelsLike"
        binding.tvWeatherMain.text = daily.weather[0].main
        binding.tvWeatherDesc.text = daily.weather[0].description
    }

}