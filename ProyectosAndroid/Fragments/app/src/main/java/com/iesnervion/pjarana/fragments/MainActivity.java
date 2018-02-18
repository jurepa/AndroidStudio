package com.iesnervion.pjarana.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener {

    Button btnFragment;
    Fragment1 fragment1;
    MainVM viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*viewModel= ViewModelProviders.of(this).get(MainVM.class);
        if(viewModel.getResultado()!=null) {
            viewModel.getResultado().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {

                }
            });
        }*/
        fragment1=new Fragment1();
        btnFragment=findViewById(R.id.btnFragment);
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragment=getFragmentManager().beginTransaction();

                fragment.add(R.id.layoutMainActivity2,fragment1);
                fragment.commit();
                btnFragment.setClickable(false);
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
        btnFragment.setClickable(true);
    }
}
