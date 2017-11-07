package com.example.pjarana.spinnergridview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Usuario on 31/10/2017.
 */

public class DefaultViewHolder //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
{
    TextView nombre;
    ImageView icono;

    public DefaultViewHolder(View row)
    {
        this.nombre =(TextView)row.findViewById(R.id.nombrePokemon);
        this.icono=(ImageView)row.findViewById(R.id.iconoPokemon);
    }

    public TextView getNombre()
    {
        return nombre;

    }
    public ImageView getIcono()
    {
        return icono;
    }
}