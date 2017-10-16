package com.iesnervion.usuario.listviewprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private static final String[]cosas={"Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise","Bulbasaur","Ivysaur","Venasaur"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView lista=(ListView)findViewById(R.id.pokemon);
        ArrayAdapter <String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cosas);
        lista.setAdapter(adapter);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
