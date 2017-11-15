package com.example.pjarana.ej5bol7;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Usuario on 31/10/2017.
 */

public class DefaultViewHolder //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
{
    ImageView imagen;

    public DefaultViewHolder(View row)
    {
        this.imagen=(ImageView)row.findViewById(R.id.imagen);
    }

    public ImageView getImagen()
    {
        return this.imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
}