package com.iesnervion.dleal.examenprimeraevaluacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesnervion.dleal.examenprimeraevaluacion.model.Imagen;
import com.iesnervion.dleal.examenprimeraevaluacion.model.Jugador;

import java.util.List;

/**
 * Created by dleal on 7/12/16.
 */

public class ArrayAdapterImagen extends ArrayAdapter{
    public ArrayAdapterImagen(Context context, int resource, List<Imagen> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolderImagen viewHolderImagen;

        if (row==null){


            row = inflater.inflate(R.layout.imagen, parent, false);

            ImageView imagen = (ImageView) row.findViewById(R.id.imagenfila);

            viewHolderImagen = new ViewHolderImagen(imagen);

            row.setTag(viewHolderImagen);



        }
        else{

            viewHolderImagen = (ViewHolderImagen) row.getTag();


        }


        Imagen img = (Imagen)getItem(position);

        ImageView imagen =  viewHolderImagen.getImagen();


        imagen.setImageResource(img.getImg());





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
