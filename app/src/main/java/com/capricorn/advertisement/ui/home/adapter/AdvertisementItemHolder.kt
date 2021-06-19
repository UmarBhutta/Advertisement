package com.capricorn.advertisement.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.capricorn.advertisement.data.dto.Advertisement
import com.capricorn.advertisement.databinding.AdvertisementItemBinding
import com.capricorn.advertisement.utils.loadImage
import net.danlew.android.joda.DateUtils
import org.joda.time.DateTime

import org.joda.time.chrono.ISOChronology

import org.joda.time.format.DateTimeFormat
import java.util.*


/**
 * Created by Muhammad Umar on 16/06/2021.
 */
class AdvertisementItemHolder(private val itemBinding: AdvertisementItemBinding):RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(advertisement: Advertisement, itemClickListener:RecyclerItemClickListener){
        itemBinding.name.text = advertisement.name
        itemBinding.price.text = advertisement.price
        var thumbnailImage = if(advertisement.imageUrlsThumbnails.isNullOrEmpty())  "" else advertisement?.imageUrlsThumbnails!!.first()
        itemBinding.thumbnail.loadImage(thumbnailImage)
        itemBinding.root.setOnClickListener {
            itemClickListener.onAdvertisementItemClick(advertisement)
        }

        setupDate(advertisement.createdAt)

    }

    private fun setupDate(date: String?) {
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
            .withLocale(Locale.ROOT)
            .withChronology(ISOChronology.getInstanceUTC())
        val dateTime: DateTime = formatter.parseDateTime(date)
        itemBinding.dateCreated.text = "Posted: " + DateUtils.getRelativeTimeSpanString(
            itemBinding.root.context,
            dateTime,
            true
        )
    }
}