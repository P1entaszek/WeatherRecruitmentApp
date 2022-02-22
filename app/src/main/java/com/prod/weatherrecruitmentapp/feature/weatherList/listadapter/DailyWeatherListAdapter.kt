package com.prod.weatherrecruitmentapp.feature.weatherList.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prod.weatherrecruitmentapp.R
import com.prod.weatherrecruitmentapp.common.getDaysListFromToday
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.DailyWeather
import kotlinx.android.synthetic.main.daily_weather_item.view.*

/**
 * Created by Piotr Jaszczurowski on 21.02.2022
 */
class DailyWeatherListAdapter(private val dailyWeatherList: List<DailyWeather>) :
    RecyclerView.Adapter<DailyWeatherListAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.daily_weather_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val dayWeather = dailyWeatherList.get(position)
        val date = getDaysListFromToday(dailyWeatherList.size).get(position)
        holder.itemView.tvValueDayDate.text = date
        holder.itemView.tvValueMorningTemp.text = dayWeather.temp?.morn.toString()
        holder.itemView.tvValueNightTemp.text = dayWeather.temp?.night.toString()
        holder.itemView.tvValueDayTemp.text = dayWeather.temp?.day.toString()
        holder.itemView.tvValueHumidity.text = dayWeather.humidity?.toString()
    }

    override fun getItemCount(): Int {
        return dailyWeatherList.size
    }

    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v)
}