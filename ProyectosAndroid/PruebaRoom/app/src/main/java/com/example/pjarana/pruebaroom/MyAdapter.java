package com.example.pjarana.pruebaroom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Usuario on 31/10/2017.
 */

public class MyAdapter<T> extends ArrayAdapter
{
    private int idFila;
    public <T> MyAdapter(Context c, int resID, ArrayList<T>objects)
    {
        super(c,resID,objects);
        this.idFila=resID;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        DefaultViewHolder vh=null;
        Persona persona=(Persona)getItem(pos);
        if(convertView==null)
        {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.row_style, parent, false);
            vh=new DefaultViewHolder(convertView,R.id.toStringPersona);
            convertView.setTag(vh);
        }
        else
        {
            vh =(DefaultViewHolder) convertView.getTag();
        }

        if(persona!=null)
        {
            vh.getToStringPersona().setText(persona.toString());
        }
        return convertView;
    }
}
