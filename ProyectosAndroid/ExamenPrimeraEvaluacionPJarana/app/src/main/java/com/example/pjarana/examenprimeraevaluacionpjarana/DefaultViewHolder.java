package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Usuario on 31/10/2017.
 */

public class DefaultViewHolder //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
{
    private TextView nombre;
    private TextView categoria;
    private TextView talla;
    private TextView color;
    private TextView precio;

    public DefaultViewHolder(View row)
    {
        this.nombre =(TextView)row.findViewById(R.id.nombre);
        this.categoria=(TextView) row.findViewById(R.id.categoria);
        this.talla=(TextView) row.findViewById(R.id.talla);
        this.color=(TextView) row.findViewById(R.id.color);
        this.precio=(TextView) row.findViewById(R.id.precio);

    }

    public TextView getNombre()
    {
        return nombre;

    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getCategoria() {
        return categoria;
    }

    public void setCategoria(TextView categoria) {
        this.categoria = categoria;
    }

    public TextView getTalla() {
        return talla;
    }

    public void setTalla(TextView talla) {
        this.talla = talla;
    }

    public TextView getColor() {
        return color;
    }

    public void setColor(TextView color) {
        this.color = color;
    }

    public TextView getPrecio() {
        return precio;
    }

    public void setPrecio(TextView precio) {
        this.precio = precio;
    }
}