package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;
import dev.kkweon.jokerenderer.JokeRendererActivity;

/** A placeholder fragment containing a simple view. */
public class MainActivityFragment extends Fragment {

    FragmentMainBinding mFragmentMainBinding;
    private InterstitialAd mInterstitialAd;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);

        mFragmentMainBinding.buttonTellJoke.setOnClickListener(this::onButtonClick);

        if (isFree()) {
            MobileAds.initialize(getContext());

            AdView mAdView = mFragmentMainBinding.adView;
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

            mInterstitialAd = new InterstitialAd(getContext());
            mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
        return mFragmentMainBinding.getRoot();
    }

    private boolean isFree() {
        return BuildConfig.IS_FREE;
    }

    private void onButtonClick(View view) {
        if (isFree()) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                mInterstitialAd.setAdListener(
                        new AdListener() {
                            @Override
                            public void onAdClosed() {
                                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                startActivity(new Intent(getContext(), JokeRendererActivity.class));
                            }
                        });
                return;
            }
        }

        // Paid version
        startActivity(new Intent(getContext(), JokeRendererActivity.class));
    }
}
