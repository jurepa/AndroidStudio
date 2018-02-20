package com.iesnervion.pjarana.pruebaantesexamen.CustomListView;

import android.view.View;
import android.widget.TextView;

import com.iesnervion.pjarana.pruebaantesexamen.R;

/**
 * Created by Usuario on 31/10/2017.
 */

public class DefaultViewHolder //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
{
    private TextView idPersona;
    private TextView nombreUsuario;

    public DefaultViewHolder(View row)
    {
        this.idPersona =(TextView)row.findViewById(R.id.txtIDUsuario);
        this.nombreUsuario=(TextView)row.findViewById(R.id.txtNombrePersona);
    }

    public TextView getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(TextView idPersona) {
        this.idPersona = idPersona;
    }

    public TextView getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(TextView nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}