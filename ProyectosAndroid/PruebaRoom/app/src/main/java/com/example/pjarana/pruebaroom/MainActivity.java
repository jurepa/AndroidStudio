package com.example.pjarana.pruebaroom;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Persona>personas;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=findViewById(R.id.lista);
        personas=new ArrayList<Persona>(Database.getDatabase(this).getUsuariosDao().getPersonas());
        lista.setAdapter(new MyAdapter<Persona>(this,R.layout.row_style,personas));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int idPersona=personas.get(position).getId();
                Intent i=new Intent(getApplicationContext(),MascotasActivity.class);
                i.putExtra("idPersona",idPersona);
                startActivity(i);
            }
        });

    }
}
