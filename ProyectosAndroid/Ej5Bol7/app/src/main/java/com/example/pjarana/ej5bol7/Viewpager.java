package com.example.pjarana.ej5bol7;

import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Viewpager extends AppCompatActivity {

    ViewPager vp;
    Bundle bundle;
    ArrayList<ImageView> imagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        vp=(ViewPager)findViewById(R.id.viewpager);
        bundle=getIntent().getExtras();
        imagenes=(ArrayList<ImageView>) bundle.get("imagenes");
    }
}
