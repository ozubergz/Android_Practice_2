package com.example.android_practice_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_practice_2.databinding.DailyLayoutBinding
import com.example.android_practice_2.model.Daily

class DailyAdapter(private val dataSet: List<Daily>, private val listener: ClickListener) : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {

    class DailyViewHolder(
            private val binding: DailyLayoutBinding,
            private val listener: ClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(daily: Daily) {
            binding.tvWeatherMain.text = daily.weather[0].main
            binding.tvTemp.text = "Temp: ${daily.main.temp.toString().substringBefore(".")}"
            binding.root.setOnClickListener {
                listener.itemClick(daily)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = DailyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

}