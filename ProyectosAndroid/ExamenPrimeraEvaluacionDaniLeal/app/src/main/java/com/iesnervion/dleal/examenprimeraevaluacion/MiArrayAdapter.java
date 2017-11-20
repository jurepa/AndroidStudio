package com.iesnervion.dleal.examenprimeraevaluacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesnervion.dleal.examenprimeraevaluacion.model.Jugador;

import java.util.List;

/**
 * Created by dleal on 7/12/16.
 */

public class MiArrayAdapter extends ArrayAdapter<Jugador> {
    public MiArrayAdapter(Context context, int resource, List<Jugador> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolderJugador viewHolder;

        if (row==null){


            row = inflater.inflate(R.layout.filajugador, parent, false);
            TextView nombre = (TextView) row.findViewById(R.id.nombre);
            ImageView imagen = (ImageView) row.findViewById(R.id.imagenjugador);
            TextView altura = (TextView) row.findViewById(R.id.altura);
            TextView peso = (TextView) row.findViewById(R.id.peso);
            TextView posicion = (TextView) row.findViewById(R.id.posicion);

            viewHolder = new ViewHolderJugador(nombre,altura,peso,posicion,imagen);

            row.setTag(viewHolder);



        }
        else{

                viewHolder = (ViewHolderJugador) row.getTag();


        }


        Jugador jugador = getItem(position);
        TextView nombre=  viewHolder.getNombre();
        ImageView imagen =  viewHolder.getImagen();
        TextView altura=  viewHolder.getAltura();
        TextView peso=  viewHolder.getPeso();
        TextView posicion=  viewHolder.getPosicion();

        nombre.setText(jugador.getNombre().toString());
        imagen.setImageResource(jugador.getImg());
        altura.setText(""+jugador.getAltura());
        peso.setText(""+jugador.getPeso());
        posicion.setText(jugador.getPosicion().toString());





        return row;
    }




    @Override
    public int getItemViewType(int position){
        return 0;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }
}

