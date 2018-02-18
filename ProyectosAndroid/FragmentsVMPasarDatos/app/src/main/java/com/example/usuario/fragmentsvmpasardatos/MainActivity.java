package com.example.usuario.fragmentsvmpasardatos;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    ResultadoFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getResultado().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if(integer!=null)
                {
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction transaccion=fragmentManager.beginTransaction();
                    fragment=ResultadoFragment.newInstance(String.valueOf(integer));
                    transaccion.add(R.id.fragmentResultado,fragment);
                    transaccion.commit();
                }
            }
        });
    }
}
