package com.capricorn.advertisement.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.capricorn.advertisement.data.dto.Advertisement
import com.capricorn.advertisement.databinding.AdvertisementItemBinding
import com.capricorn.advertisement.utils.loadImage

/**
 * Created by Muhammad Umar on 16/06/2021.
 */
class AdvertisementItemHolder(private val itemBinding: AdvertisementItemBinding):RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(advertisement: Advertisement, itemClickListener:RecyclerItemClickListener){
        itemBinding.name.text = advertisement.name
        var thumbnailImage = if(advertisement.imageUrlsThumbnails.isNullOrEmpty())  "" else advertisement?.imageUrlsThumbnails!!.first()
        itemBinding.thumbnail.loadImage(thumbnailImage)
        itemBinding.root.setOnClickListener {
            itemClickListener.onAdvertisementItemClick(advertisement)
        }
    }
}