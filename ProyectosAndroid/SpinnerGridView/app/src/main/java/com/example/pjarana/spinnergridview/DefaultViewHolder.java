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
    TextView tipo;
    TextView nivelEvolucion;
    ImageView icono;

    public DefaultViewHolder(View row, int nombrePokemon, int tipo, int nivel, int icono)
    {
        this.nombre =(TextView)row.findViewById(nombrePokemon);
        this.tipo=(TextView)row.findViewById(tipo);
        this.nivelEvolucion=(TextView)row.findViewById(nivel);
        this.icono=(ImageView)row.findViewById(icono);
    }

    public TextView getNombre()
    {
        return nombre;

    }
    public ImageView getIcono()
    {
        return icono;
    }
    public TextView getTipo() {
        return tipo;
    }

    public TextView getNivelEvolucion() {
        return nivelEvolucion;
    }
}