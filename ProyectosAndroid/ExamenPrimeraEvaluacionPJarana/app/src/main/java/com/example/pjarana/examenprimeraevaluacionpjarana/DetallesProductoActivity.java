package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class DetallesProductoActivity extends AppCompatActivity {

    TextView nombreProducto;
    ImageView imagenProducto;
    Spinner spinnerTallas;
    Spinner spinnerColores;
    TextView precioProducto;
    TextView descripcionProducto;
    String productoSeleccionado;
    Producto p;
    ArrayList<Integer>tallas;
    ArrayList<String>colores;
    ArrayList<Producto>listadoProductos;
    ArrayAdapter<Integer>tallasAdapter;
    ArrayAdapter<String>coloresAdapter;
    Button btnAddCesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);
        productoSeleccionado=getIntent().getStringExtra("producto");
        p=((ListadoProductos)getApplication()).buscarProducto(productoSeleccionado);
        nombreProducto=(TextView)findViewById(R.id.nombreProducto);
        nombreProducto.setText(p.getNombre());
        spinnerTallas=(Spinner)findViewById(R.id.spinnerTallas);
        spinnerColores=(Spinner)findViewById(R.id.spinnerColores);
        precioProducto=(TextView)findViewById(R.id.precioProducto);
        precioProducto.setText(String.valueOf(p.getPrecio()));
        imagenProducto=(ImageView)findViewById(R.id.imagenProducto);
        descripcionProducto=(TextView)findViewById(R.id.descripcionProducto);
        descripcionProducto.setText(p.getDescripcion());
        btnAddCesta=(Button)findViewById(R.id.addCesta);
        //Por defecto, al entrar en los detalles del producto, pondremos siempre el primero que encontremos en nuestra lista
        if(p.getCategoria().equals("Moda"))
        {
            tallas=((ListadoProductos)getApplication()).arrayTallas(p);
            tallasAdapter=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_spinner_item,tallas);
            tallasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTallas.setAdapter(tallasAdapter);

            colores=((ListadoProductos)getApplication()).arrayColoresModa((Integer) spinnerTallas.getSelectedItem(),p);
            coloresAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,colores);
            coloresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerColores.setAdapter(coloresAdapter);
            imagenProducto.setImageResource(((ListadoProductos) getApplication()).imagenProductoModa(p,(String)spinnerColores.getSelectedItem()));
        }
        else
        {
            tallas=new ArrayList<Integer>();
            tallasAdapter=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_spinner_item,tallas);
            tallasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTallas.setAdapter(tallasAdapter);

            colores=((ListadoProductos)getApplication()).arrayColoresMueble(p);
            coloresAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,colores);
            coloresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerColores.setAdapter(coloresAdapter);
            imagenProducto.setImageResource(((ListadoProductos) getApplication()).imagenProductoMueble(p,(String)spinnerColores.getSelectedItem()));

        }
        spinnerTallas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(p.getCategoria().equals("Moda"))
                {
                    p=((ListadoProductos)getApplication()).buscarProductoPorTallaNombre(p.getNombre(),(Integer) spinnerTallas.getSelectedItem());
                    precioProducto.setText(String.valueOf(p.getPrecio()));
                    colores=((ListadoProductos)getApplication()).arrayColoresModa((Integer) spinnerTallas.getSelectedItem(),p);
                    coloresAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,colores);
                    spinnerColores.setAdapter(coloresAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerColores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imagenProducto.setImageResource(((ListadoProductos) getApplication()).imagenProductoModa(p,(String)spinnerColores.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAddCesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),CestaActivity.class);
                ProductoAComprar producto=new ProductoAComprar();
                producto.setNombre(nombreProducto.getText().toString());
                producto.setCategoria(p.getCategoria());
                producto.setColor((String)spinnerColores.getSelectedItem());
                producto.setDescripcion(descripcionProducto.getText().toString());
                producto.setPrecio(Integer.parseInt(precioProducto.getText().toString()));
                //producto.setImagen(); //Tengo que cambiar unas cosas y no me da tiempo
                producto.setTalla((Integer)spinnerTallas.getSelectedItem());
                ((ListadoProductos)getApplication()).getCesta().add(producto);
                startActivity(i);
                finish();
            }
        });
    }
}
