package com.iesnervion.pjarana.ejercicio3bol1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void alinear(View v)
    {
        EditText texto=(EditText)findViewById(R.id.texto);
        Button botonDerecha=(Button)findViewById(R.id.derecha);
        Button botonIzquierda=(Button)findViewById(R.id.izquierda);
        if(v.getId()==R.id.derecha)
        {
            texto.setGravity(Gravity.RIGHT);
        }
        else
        {
            texto.setGravity(Gravity.LEFT);
        }
    }
}
