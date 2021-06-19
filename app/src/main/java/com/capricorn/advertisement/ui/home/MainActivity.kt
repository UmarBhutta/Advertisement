package com.capricorn.advertisement.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.capricorn.advertisement.ADVERTISEMENT_ITEM_KEY
import com.capricorn.advertisement.R
import com.capricorn.advertisement.data.Result
import com.capricorn.advertisement.data.dto.Advertisement
import com.capricorn.advertisement.data.dto.AdvertisementList
import com.capricorn.advertisement.databinding.MainActivityBinding
import com.capricorn.advertisement.ui.base.BaseActivity
import com.capricorn.advertisement.ui.details.AdvertisementDetailsActivity
import com.capricorn.advertisement.ui.home.adapter.AdvertisementListAdapter
import com.capricorn.advertisement.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var binding: MainActivityBinding
    private lateinit var advertisementListAdapter: AdvertisementListAdapter



    override fun initViewBinding() {
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.app_name)
        val layoutManager = LinearLayoutManager(this)
        binding.rvPokemonList.layoutManager = layoutManager
        binding.rvPokemonList.setHasFixedSize(true)
        mainViewModel.getAdvertisementList()

    }


    private fun bindListData(advertisementList: AdvertisementList) {
        if (!(advertisementList.results.isNullOrEmpty())) {
            advertisementListAdapter = AdvertisementListAdapter(mainViewModel, advertisementList.results)
            binding.rvPokemonList.adapter = advertisementListAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<Advertisement>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, AdvertisementDetailsActivity::class.java).apply {
                putExtra(ADVERTISEMENT_ITEM_KEY,it)
            }
            startActivity(nextScreenIntent)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }



    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) GONE else VISIBLE
        binding.rvPokemonList.visibility = if (show) VISIBLE else GONE
        binding.pbLoading.gone()
    }

    private fun showLoadingView() {
        binding.pbLoading.visible()
        binding.tvNoData.gone()
        binding.rvPokemonList.gone()
    }



    private fun handlePokemonList(status: Result<AdvertisementList>) {
        when (status) {
            is Result.Loading -> showLoadingView()
            is Result.Success -> status.data?.let { bindListData(advertisementList = it) }
            is Result.Error -> {
                showDataView(false)
                status.errorCode?.let { mainViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        mainViewModel.advertisementList.observe(this,::handlePokemonList)
        mainViewModel.openAdvertisementDetails.observe(this,::navigateToDetailsScreen)
        observeSnackBarMessages(mainViewModel.showSnackBar)
        observeToast(mainViewModel.showToast)

    }
}