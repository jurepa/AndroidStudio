package com.iesnervion.usuario.spinnerlistview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Usuario on 31/10/2017.
 */

public class MyAdapter<T> extends ArrayAdapter
{
    private int idFila;
    public <T> MyAdapter(Context c, int resID, T[]objects)
    {
        super(c,resID,objects);
        this.idFila=resID;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        DefaultViewHolder vh=null;
        DefaultViewHolder vh2=null;
        DefaultViewHolder vh3=null;
        Pokemon pokemon=(Pokemon)getItem(pos);
        if(convertView==null)
        {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch(idFila){
                case R.layout.filatipofuego:
                    convertView = li.inflate(R.layout.filatipofuego, parent, false);
                    vh=new DefaultViewHolder(convertView,R.id.nombrePokemon,R.id.tipo,R.id.nivelEvolucion,R.id.imagenPokemon);
                    convertView.setTag(vh);
                break;
                case R.layout.filatipoagua:
                    convertView = li.inflate(R.layout.filatipoagua, parent, false);
                    vh2=new DefaultViewHolder(convertView,R.id.nombrePokemonAgua,R.id.tipoAgua,R.id.nivelEvolucionAgua,R.id.imagenPokemonAgua);
                    convertView.setTag(vh2);
                    break;
                case R.layout.filatipoplanta:
                    convertView = li.inflate(R.layout.filatipoplanta, parent, false);
                    vh3=new DefaultViewHolder(convertView,R.id.nombrePlanta,R.id.tipoPlanta,R.id.nivelEvolucionPlanta,R.id.imagenPlanta);
                    convertView.setTag(vh3);
                    break;
            }
        }
        else
        {
            switch(idFila) {
                case R.layout.filatipofuego:
                    vh = (DefaultViewHolder) convertView.getTag();
                    break;
                case R.layout.filatipoagua:
                    vh2 = (DefaultViewHolder) convertView.getTag();
                    break;
                case R.layout.filatipoplanta:
                    vh3 = (DefaultViewHolder) convertView.getTag();
                    break;
            }
        }

        if(pokemon!=null)
        {
            switch(idFila) {
                case R.layout.filatipofuego:

                    vh.nombre.setText(pokemon.getNombre());
                    vh.nivelEvolucion.setText("Nivel de evolución: " + pokemon.getNivelEvolucion());
                    if (pokemon.getTipo().length == 2) {
                        vh.tipo.setText("Tipos: " + pokemon.getTipo()[0] + " y " + pokemon.getTipo()[1]);
                    } else {
                        vh.tipo.setText("Tipo: " + pokemon.getTipo()[0]);
                    }
                    if(pokemon.getTipo()[0].equals("Fuego"))
                    {
                        vh.nombre.setTextColor(Color.RED);
                    }
                    else if(pokemon.getTipo()[0].equals("Agua"))
                    {
                        vh.nombre.setTextColor(Color.BLUE);
                    }
                    else
                    {
                        vh.nombre.setTextColor(Color.GREEN);
                    }
                    vh.icono.setImageResource(pokemon.getIcono());
                    vh.nombre.setTag(pokemon.hashCode());
                    break;
                case R.layout.filatipoagua:
                    vh2.nombre.setText(pokemon.getNombre());
                    vh2.nivelEvolucion.setText("Nivel de evolución: " + pokemon.getNivelEvolucion());
                    if (pokemon.getTipo().length == 2) {
                        vh2.tipo.setText("Tipos: " + pokemon.getTipo()[0] + " y " + pokemon.getTipo()[1]);
                    } else {
                        vh2.tipo.setText("Tipo: " + pokemon.getTipo()[0]);
                    }
                    vh2.nombre.setTextColor(Color.BLUE);
                    vh2.icono.setImageResource(pokemon.getIcono());
                    vh2.nombre.setTag(pokemon.hashCode());
                    break;
                case R.layout.filatipoplanta:
                    vh3.nombre.setText(pokemon.getNombre());
                    vh3.nivelEvolucion.setText("Nivel de evolución: " + pokemon.getNivelEvolucion());
                    if (pokemon.getTipo().length == 2) {
                        vh3.tipo.setText("Tipos: " + pokemon.getTipo()[0] + " y " + pokemon.getTipo()[1]);
                    } else {
                        vh3.tipo.setText("Tipo: " + pokemon.getTipo()[0]);
                    }
                    vh3.nombre.setTextColor(Color.GREEN);
                    vh3.icono.setImageResource(pokemon.getIcono());
                    vh3.nombre.setTag(pokemon.hashCode());
                    break;
            }

        }
        return convertView;
    }
}
