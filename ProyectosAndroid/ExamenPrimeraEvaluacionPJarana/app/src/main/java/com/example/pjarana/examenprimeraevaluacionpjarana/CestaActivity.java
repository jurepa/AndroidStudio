package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class CestaActivity extends AppCompatActivity {

    ProductoAComprar p;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);
        p=(ProductoAComprar) getIntent().getParcelableExtra("producto");
        lista=(ListView)findViewById(R.id.cesta);
        MyAdapter<ProductoAComprar>listaAdapter=new MyAdapter<ProductoAComprar>(getApplicationContext(),R.layout.listview_style,((ListadoProductos)getApplicationContext()).getCesta());
        lista.setAdapter(listaAdapter);


    }
}
