package com.iesnervion.usuario.listviewprueba;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String[]cosas={"Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise","Bulbasaur","Ivysaur","Venasaur", "Snorlax"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ListView lista=(ListView)findViewById(R.id.pokemon);
        IconicAdapter <String> adapter=new IconicAdapter<String>(this,R.layout.estilo_lista, R.id.nombrePokemon, cosas);
        lista.setAdapter(adapter);
        super.onCreate(savedInstanceState);
    }
    class IconicAdapter<T> extends ArrayAdapter<T>
    {
        public IconicAdapter(Context c, int resourceId, int textId, T[]objects)
        {
            super(c,resourceId,textId,objects);
        }
        public View getView(int posicion, View convertView, ViewGroup parent)
        {
            View fila = super.getView(posicion, convertView, parent);
            ImageView imagen=(ImageView)fila.findViewById(R.id.imagenPokemon);
            TextView nombre=(TextView)fila.findViewById(R.id.nombrePokemon);
            switch (cosas[posicion]) {
                case "Charmander":
                    imagen.setImageResource(R.drawable.charmander);
                    nombre.setTextColor(Color.RED);
                    break;
                case "Charmeleon":
                    imagen.setImageResource(R.drawable.charmeleon_icon);
                    nombre.setTextColor(Color.RED);
                    break;
                case "Charizard":
                    imagen.setImageResource(R.drawable.charizard_icon);
                    nombre.setTextColor(Color.RED);
                    break;
                case "Squirtle":
                    imagen.setImageResource(R.drawable.squirtle);
                    nombre.setTextColor(Color.BLUE);
                    break;
                case "Wartortle":
                    imagen.setImageResource(R.drawable.wartortle_icon);
                    nombre.setTextColor(Color.BLUE);
                    break;
                case "Blastoise":
                    imagen.setImageResource(R.drawable.blastoise_icon);
                    nombre.setTextColor(Color.BLUE);
                    break;
                case "Bulbasaur":
                    imagen.setImageResource(R.drawable.bullbasaur);
                    nombre.setTextColor(Color.GREEN);
                    break;
                case "Ivysaur":
                    imagen.setImageResource(R.drawable.ivysaur_icon);
                    nombre.setTextColor(Color.GREEN);
                    break;
                case "Venasaur":
                    imagen.setImageResource(R.drawable.venusaur_icon);
                    nombre.setTextColor(Color.GREEN);
                    break;
                default:
                    imagen.setImageResource(R.drawable.ultrabola);
                    nombre.setTextColor(Color.BLACK);
                    break;
            }
            return fila;
        }
    }
}
