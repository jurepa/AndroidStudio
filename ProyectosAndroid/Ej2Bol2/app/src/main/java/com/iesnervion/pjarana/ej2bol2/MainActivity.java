package com.iesnervion.pjarana.ej2bol2;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calculadora(View v)
    {   int numero1int,numero2int,resultadoint;
        TextView resultado=(TextView)findViewById(R.id.resultado);
        EditText numero1=(EditText)findViewById(R.id.numero1);
        EditText numero2=(EditText)findViewById(R.id.numero2);

        switch(v.getId())
        {
            case R.id.sumar:
                numero1int=Integer.parseInt(numero1.getText().toString());
                numero2int=Integer.parseInt(numero2.getText().toString());
                resultadoint=numero1int+numero2int;
                resultado.setText(String.valueOf(resultadoint));
                break;
            case R.id.restar:
                numero1int=Integer.parseInt(numero1.getText().toString());
                numero2int=Integer.parseInt(numero2.getText().toString());
                resultadoint=numero1int-numero2int;
                resultado.setText(String.valueOf(resultadoint));
                break;
            case R.id.multiplicar:
                numero1int=Integer.parseInt(numero1.getText().toString());
                numero2int=Integer.parseInt(numero2.getText().toString());
                resultadoint=numero1int*numero2int;
                resultado.setText(String.valueOf(resultadoint));
                break;
            case R.id.dividir:
                numero1int=Integer.parseInt(numero1.getText().toString());
                numero2int=Integer.parseInt(numero2.getText().toString());
                resultadoint=numero1int/numero2int;
                resultado.setText(String.valueOf(resultadoint));
                break;
        }
    }
}
