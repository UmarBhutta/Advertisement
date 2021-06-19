package com.capricorn.advertisement.ui.home.adapter

import com.capricorn.advertisement.data.dto.Advertisement

/**
 * Created by Muhammad Umar on 16/06/2021.
 */
interface RecyclerItemClickListener {
    fun onAdvertisementItemClick(advertisement: Advertisement)
}