package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView animationView;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView = view.findViewById(R.id.animation_view);
        listView = view.findViewById(R.id.list_view);
        listView.setAdapter(new ListAdapter());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setVisibility(View.VISIBLE);

                ObjectAnimator animatorAlpha1 = ObjectAnimator.ofFloat(animationView,
                        "alpha", 1f, 0f);
                animatorAlpha1.setDuration(500);

                ObjectAnimator animatorAlpha2 = ObjectAnimator.ofFloat(listView,
                        "alpha", 0f, 1f);
                animatorAlpha2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animationView.setVisibility(View.INVISIBLE);
                    }
                });

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animatorAlpha1, animatorAlpha2);
                animatorSet.start();
            }
        }, 5000);
    }
}
