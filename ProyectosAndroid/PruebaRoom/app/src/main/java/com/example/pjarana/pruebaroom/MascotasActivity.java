package com.example.pjarana.pruebaroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MascotasActivity extends AppCompatActivity {

    ArrayList<Mascota>listaMascotas;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        Intent i=getIntent();
        int id=i.getIntExtra("idPersona",0);
        listaMascotas=new ArrayList<Mascota>(Database.getDatabase(this).getUsuariosDao().getMascotasFromPersona(id));
        lista=(ListView)findViewById(R.id.listaMascotas);
        lista.setAdapter(new <Mascota> MyAdapterMascotas(this,R.layout.row_style,listaMascotas));
    }
}
