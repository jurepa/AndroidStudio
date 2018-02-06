package com.iesnervion.pjarana.fragments;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {

    Button btnFragment;
    Fragment1 fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1=new Fragment1();
        btnFragment=findViewById(R.id.btnFragment);
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragment=getFragmentManager().beginTransaction();

                fragment.add(R.id.layoutMainActivity,fragment1);
                fragment.commit();
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void removeFragment(View v) {
        FragmentTransaction fragment=getFragmentManager().beginTransaction();
        fragment.remove(fragment1);
        fragment.commit();
    }
}
