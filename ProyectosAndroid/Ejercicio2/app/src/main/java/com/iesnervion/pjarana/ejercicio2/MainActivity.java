package com.iesnervion.pjarana.ejercicio2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.graphics.Color.RED;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void botonPulsado (View v)
    {
        EditText texto=(EditText)findViewById(R.id.editText);
        Button rojo=(Button)findViewById(R.id.rojo);
        Button verde=(Button)findViewById(R.id.verde);
        Button azul=(Button)findViewById(R.id.azul);
        switch(v.getId())
        {
            case R.id.azul:
                texto.setTextColor(Color.BLUE);
                break;
            case R.id.rojo:
                texto.setTextColor(Color.RED);
                break;
            case R.id.verde:
                texto.setTextColor(Color.GREEN);
                break;

        }


    }
}
