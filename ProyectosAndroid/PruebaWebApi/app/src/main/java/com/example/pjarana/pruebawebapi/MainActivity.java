package com.example.pjarana.pruebawebapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView toString;
    private final static String SERVER_URL = "http://10.0.2.2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit;
        LibroCallback libroCallback = new LibroCallback();


        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LibroInterface libroInter = retrofit.create(LibroInterface.class);

        libroInter.getLibro(1).enqueue(libroCallback);
        toString=(TextView)findViewById(R.id.txtString);
  //      toString.setText(libroCallback.getLibro().toString());
    }

}
