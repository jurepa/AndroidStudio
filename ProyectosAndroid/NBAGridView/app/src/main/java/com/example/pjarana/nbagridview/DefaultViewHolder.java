package com.example.pjarana.nbagridview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Usuario on 31/10/2017.
 */

public class DefaultViewHolder //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
{
    private TextView nombre;
    private ImageView imagen;

    public DefaultViewHolder(View row)
    {
        this.nombre =(TextView)row.findViewById(R.id.nombreEquipo);
        this.imagen=(ImageView)row.findViewById(R.id.logoEquipo);
    }

    public TextView getNombre()
    {
        return nombre;

    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
}