package com.example.pjarana.ej1bol7;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Ej1Bol2Activity extends AppCompatActivity /*implements CompoundButton.OnCheckedChangeListener*/{
    CheckBox negrita;
    CheckBox masTamaño;
    CheckBox menosTamaño;
    CheckBox rojo;
    int tamaño=18;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej1bol2);
        /*negrita.setOnCheckedChangeListener(this);
        masTamaño.setOnCheckedChangeListener(this);
        menosTamaño.setOnCheckedChangeListener(this);
        rojo.setOnCheckedChangeListener(this);*/
    }
    public void checkeado (View v)
    {
        negrita=(CheckBox)findViewById(R.id.negrita);
        masTamaño=(CheckBox)findViewById(R.id.masTamaño);
        menosTamaño=(CheckBox)findViewById(R.id.menosTamaño);
        rojo=(CheckBox)findViewById(R.id.colorRojo);
        EditText texto=(EditText)findViewById(R.id.texto);
        switch(v.getId())
        {
            case R.id.negrita:
                if(negrita.isChecked())
                {
                    texto.setTypeface(null, Typeface.BOLD);
                }
                else
                {
                    texto.setTypeface(null, Typeface.NORMAL);
                }
                break;
            case R.id.masTamaño:
                if(masTamaño.isChecked())
                {
                    tamaño+=10;
                    texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tamaño);
                    if(menosTamaño.isChecked())
                    {
                        tamaño+=10;
                        menosTamaño.toggle();
                        texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tamaño);
                    }
                }
                else
                {
                    tamaño-=10;
                    texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tamaño);
                }
                break;
            case R.id.menosTamaño:
                if(menosTamaño.isChecked())
                {
                    tamaño-=10;
                    texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tamaño);
                    if(masTamaño.isChecked())
                    {
                        tamaño-=10;
                        masTamaño.toggle();
                        texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tamaño);
                    }
                }
                else
                {
                    tamaño+=10;
                    texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tamaño);
                }
                break;
            case R.id.colorRojo:
                if(rojo.isChecked())
                {
                    texto.setTextColor(Color.RED);
                }
                else
                {
                    texto.setTextColor(Color.BLACK);
                }
                break;
        }
    }
}
