package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.content.Intent;
import android.graphics.*;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/*
* ANOTACIONES: El list view no funciona no he tenido tiempo de verlo bien, lo demás funciona correctamente
*-Que el precio cambie tampoco está implementado.
* */
public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView text;
    Button btnProducto;
    String productoSeleccionado=null;
    Intent i;
    private final String[] PRODUCTOS={"","Zapato","Camiseta","Pantalon","Mesa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.texto);
        btnProducto=(Button)findViewById(R.id.btnDetalles);
        spinner=(Spinner)findViewById(R.id.spinnerProductos);
        ArrayAdapter<String>spinnerAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,PRODUCTOS);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String itemSelected=PRODUCTOS[position];
                switch (itemSelected)
                {
                    case "Zapato":
                    case "Camiseta":
                    case "Pantalon":
                    case "Mesa":
                        productoSeleccionado=itemSelected;
                        break;
                    case "":
                        productoSeleccionado=null;
                        break;
                }
                if(productoSeleccionado==null)
                {
                    btnProducto.setClickable(false);
                }
                else
                {
                    btnProducto.setClickable(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                text.setTextColor(Color.RED);
            }
        });
        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getApplicationContext(),DetallesProductoActivity.class);
                i.putExtra("producto",productoSeleccionado);
                startActivity(i);
            }
        });

    }
}
