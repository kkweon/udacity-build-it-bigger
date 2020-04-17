package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;
import dev.kkweon.jokerenderer.JokeRendererActivity;

/** A placeholder fragment containing a simple view. */
public class MainActivityFragment extends Fragment {

    FragmentMainBinding mFragmentMainBinding;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);

        mFragmentMainBinding.buttonTellJoke.setOnClickListener(this::onButtonClick);

        MobileAds.initialize(getContext());

        AdView mAdView = mFragmentMainBinding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        return mFragmentMainBinding.getRoot();
    }

    private void onButtonClick(View view) {
        startActivity(new Intent(getContext(), JokeRendererActivity.class));
    }
}
