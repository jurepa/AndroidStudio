package com.example.pjarana.pruebaroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MascotasActivity extends AppCompatActivity {

    ListView lista;
    Button btn;
    ViewModelMascotas vm;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        btn=findViewById(R.id.verMascotas);
        lista=(ListView)findViewById(R.id.listaMascotas);
        Intent i=getIntent();
        id=i.getIntExtra("idPersona",0);
        vm= ViewModelProviders.of(this).get(ViewModelMascotas.class);
        //vm.cargarListaMascotasDeUsuario(getApplication(),id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.cargarListaMascotasDeUsuario(getApplication(),id);
                vm.getMascotas().observe(MascotasActivity.this, new Observer<List<Mascota>>() {
                    @Override
                    public void onChanged(@Nullable List<Mascota> mascotas) {
                        lista.setAdapter(new <Mascota> MyAdapterMascotas(getApplicationContext(),R.layout.row_style,new ArrayList<Mascota>(vm.getMascotas().getValue())));
                    }
                });
            }
        });
    }
}
