package com.iesnervion.pjarana.ej5bol1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    int tamanio=18;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void tamanioLetra(View v)
    {
        Button aumentar=(Button)findViewById(R.id.aumentar);
        Button disminuir=(Button)findViewById(R.id.disminuir);
        EditText texto=(EditText) findViewById(R.id.texto);
        if(v.getId()==aumentar.getId())
        {
            tamanio+=2;
            texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tamanio);
        }
        else
        {
            tamanio-=2;
            texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tamanio);
        }
    }
}
