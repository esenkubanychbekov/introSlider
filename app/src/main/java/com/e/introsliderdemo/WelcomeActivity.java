package com.e.introsliderdemo;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager mPager;
    private int[] layouts = {R.layout.first_slide,
            R.layout.second_slide, R.layout.third_slide,
            R.layout.four_slide};

    private MpagerAdapter mpagerAdapter;
    private LinearLayout Dots_layout;
    private ImageView[] dots;
    private Button BnNext, BnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (new PrefaranceManager(this).checkPreferance()) {
            loadHome();
        }


        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        setContentView(R.layout.activity_welcome);

        mPager = findViewById(R.id.viewPager);
        mpagerAdapter = new MpagerAdapter(layouts, this);
        mPager.setAdapter(mpagerAdapter);
        Dots_layout = findViewById(R.id.dotsLayout);
        BnSkip = findViewById(R.id.bnSkip);
        BnNext = findViewById(R.id.bnNext);
        BnSkip.setOnClickListener(this);
        BnNext.setOnClickListener(this);
        createDots(0);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                createDots(i);
                if (i==layouts.length-1){
                    BnNext.setText("Start");
                    BnSkip.setVisibility(View.INVISIBLE);
                }
                else {
                    BnNext.setText("Next");
                    BnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    public void createDots(int current_possition) {
        if (Dots_layout != null) {
            Dots_layout.removeAllViews();

            dots = new ImageView[layouts.length];

            for (int i = 0; i < layouts.length; i++) {
                dots[i] = new ImageView(this);
                if (i == current_possition) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
                } else {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dots));
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(4, 0, 4, 0);
                Dots_layout.addView(dots[i], params);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bnNext:
                loadNextSlide();
                break;

            case R.id.bnSkip:
                loadHome();
                new PrefaranceManager(this).writePreference();
                break;
        }
    }

    private void loadHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void loadNextSlide() {
        int next_slide = mPager.getCurrentItem() + 1;

        if (next_slide < layouts.length) {
            mPager.setCurrentItem(next_slide);
        } else {
            loadHome();
            new PrefaranceManager(this).writePreference();
        }
    }
}
