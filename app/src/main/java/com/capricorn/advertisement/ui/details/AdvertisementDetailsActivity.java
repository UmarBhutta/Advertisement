package com.capricorn.advertisement.ui.details;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.capricorn.advertisement.R;
import com.capricorn.advertisement.data.dto.Advertisement;
import com.capricorn.advertisement.databinding.ActivityAdvertisementDetailsBinding;
import com.capricorn.advertisement.ui.base.BaseActivity;
import dagger.hilt.android.AndroidEntryPoint;

import static com.capricorn.advertisement.ConstantsKt.ADVERTISEMENT_ITEM_KEY;


@AndroidEntryPoint
public class AdvertisementDetailsActivity extends BaseActivity {

    private DetailsViewModel detailsViewModel;
    private ActivityAdvertisementDetailsBinding binding;

    @Override
    protected void initViewBinding() {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        binding = ActivityAdvertisementDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsViewModel.initFromIntentData(getIntent().getParcelableExtra(ADVERTISEMENT_ITEM_KEY));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void observeViewModel() {
        detailsViewModel.getAdvertisementData().observe(this,this::setupView);
    }

    private void setupView(Advertisement advertisement){
        getSupportActionBar().setTitle(advertisement.getName());
        loadImage(advertisement.getImageUrls().get(0));
    }

    private void loadImage(String url){
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_baseline_perm_identity_24).centerInside();
        Glide.with(this).load(url)
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.pokemonImage);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}