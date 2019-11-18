package com.example.wateryourself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CupsActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<Model> models;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.cup200, "Small cup", "200 ml"));
        models.add(new Model(R.drawable.bottle, "Small bottle", "500 ml"));
        //todo: add the other pics

        adapter = new Adapter(models, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        //todo:add 2 more colors
        Integer[] colors_temp = {getResources().getColor(R.color.cup200color),
                getResources().getColor(R.color.cup500color)        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount()-1) && position < (colors.length-1)){
                    viewPager.setBackgroundColor((Integer)argbEvaluator.evaluate(positionOffset,
                            colors[position],colors[position+1]));
                }
                else {
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}
