package com.capricorn.advertisement.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capricorn.advertisement.data.dto.Advertisement
import com.capricorn.advertisement.databinding.AdvertisementItemBinding
import com.capricorn.advertisement.ui.home.MainViewModel

/**
 * Created by Muhammad Umar on 16/06/2021.
 */
class AdvertisementListAdapter(private val mainViewModel: MainViewModel, private val advertisementList: List<Advertisement>) : RecyclerView.Adapter<AdvertisementItemHolder>(){

    private val onItemClickListener = object : RecyclerItemClickListener{
        override fun onAdvertisementItemClick(advertisement: Advertisement) {
            //open details screen
            mainViewModel.showAdvertisementDetails(advertisement)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementItemHolder {
        val itemBinding = AdvertisementItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdvertisementItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AdvertisementItemHolder, position: Int) {
        holder.bind(advertisementList[position],onItemClickListener)
    }

    override fun getItemCount(): Int {
        return advertisementList.size
    }
}