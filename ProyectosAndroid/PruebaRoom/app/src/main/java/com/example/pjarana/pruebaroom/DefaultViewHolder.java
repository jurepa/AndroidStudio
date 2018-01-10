package com.example.pjarana.pruebaroom;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Usuario on 31/10/2017.
 */

public class DefaultViewHolder //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
{
    private TextView toStringPersona;

    public DefaultViewHolder(View row, int toStringPersona)
    {
        this.toStringPersona =(TextView)row.findViewById(R.id.toStringPersona);
    }

    public TextView getToStringPersona() {
        return toStringPersona;
    }

    public void setToStringPersona(TextView toStringPersona) {
        this.toStringPersona = toStringPersona;
    }
}