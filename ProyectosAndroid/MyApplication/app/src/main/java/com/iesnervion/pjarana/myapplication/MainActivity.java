package com.iesnervion.pjarana.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void MostrarEscrito (View v)
    {
        TextView texto;
        EditText textoEscrito;
        String mensaje;
        texto=(TextView)findViewById(R.id.textview);
        textoEscrito=(EditText)findViewById(R.id.edittext);
        mensaje=textoEscrito.getText().toString();
        texto.setText(mensaje);
    }
}
