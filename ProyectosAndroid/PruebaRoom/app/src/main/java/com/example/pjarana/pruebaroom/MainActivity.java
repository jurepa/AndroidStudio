package com.example.pjarana.pruebaroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ViewModelPersonas vm;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.verUsuarios);
        lista=findViewById(R.id.lista);
        vm= ViewModelProviders.of(this).get(ViewModelPersonas.class);
        vm.getPersonas().observe(this, new Observer() { //Entra siempre cuando iniciamos la app porque al instanciar el viewmodel, cargamos la lista de personas
            @Override
            public void onChanged(@Nullable Object o) { //Entra cuando cambia una confi del tlf o el valor de alguna de las propiedades del vm
                //vm.cargarLista(getApplication()); No hace falta cargar la lista otra vez porq el vm la mantiene
                lista.setAdapter(new MyAdapter<Persona>(getApplicationContext(),R.layout.row_style,new ArrayList<Persona>(vm.getPersonas().getValue())));
            }
        });
        //personas=new ArrayList<Persona>(Database.getDatabase(this).getUsuariosDao().getPersonas());
        //lista.setAdapter(new MyAdapter<Persona>(this,R.layout.row_style,personas));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int idPersona=vm.getPersonas().getValue().get(position).getId();
                Intent i=new Intent(getApplicationContext(),MascotasActivity.class);
                i.putExtra("idPersona",idPersona);
                startActivity(i);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.cargarLista(getApplication());
            }
        });

    }
}
