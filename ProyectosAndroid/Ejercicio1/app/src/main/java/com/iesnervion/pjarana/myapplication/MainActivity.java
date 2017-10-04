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
    public void Suma (View v)
    {
        EditText numero1E;
        EditText numero2E;
        int numero1;
        int numero2;
        TextView resultado;
        int resultadoTotal;
        String mensajeFinal;
        numero1E=(EditText)findViewById(R.id.numero1);
        numero2E=(EditText)findViewById(R.id.numero2);
        resultado=(TextView)findViewById(R.id.resultado);
        numero1=Integer.parseInt(numero1E.getText().toString());
        numero2=Integer.parseInt(numero2E.getText().toString());
        resultadoTotal=numero1+numero2;
        mensajeFinal=String.valueOf(resultadoTotal);
        resultado.setText(mensajeFinal);
    }
}
