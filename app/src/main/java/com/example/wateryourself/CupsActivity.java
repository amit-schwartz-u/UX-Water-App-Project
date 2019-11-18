package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CupsActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    Drawable[] backgrounds = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<Model> models;

    int currentImage = 0;
    int[] counters = {0,0,0,0};
    int currentAmount = 0; //todo:extract from JSON


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.cup200));
        models.add(new Model(R.drawable.cup500));
        models.add(new Model(R.drawable.bottle750));
        models.add(new Model(R.drawable.bottle1));


        adapter = new Adapter(models, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Drawable[] background_temp = {getResources().getDrawable(R.drawable.back1),
                getResources().getDrawable(R.drawable.back2),
                getResources().getDrawable(R.drawable.back3),
                getResources().getDrawable(R.drawable.back4)};

        backgrounds = background_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (backgrounds.length - 1)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewPager.setBackground(backgrounds[position]);
                    }
//                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset,
//                            backgrounds[position], backgrounds[position + 1]));
                } else {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewPager.setBackground(backgrounds[backgrounds.length-1]);
                    }
                }
                currentImage = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    public void addAmount(View view) {

//        ImageView im = (ImageView)view;
//        Drawable image = im.getDrawable();
        counters[currentImage]++;
        Toast toast = Toast.makeText(getApplicationContext(),
                String.valueOf(counters[currentImage]),Toast.LENGTH_SHORT);
        toast.show();
    }
}
