package com.example.pjarana.spinnergridview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Usuario on 31/10/2017.
 */

public class MyAdapter<T> extends BaseAdapter
{
    private Context c;
    private ArrayList<Pokemon> items;



    public <T> MyAdapter(Context c, ArrayList<Pokemon>items)
    {
        this.items=items;
        this.c=c;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        DefaultViewHolder vh=null;

        Pokemon pokemon=(Pokemon)getItem(pos);
        if(convertView==null)
        {
            LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.grid_style, parent, false);
            vh=new DefaultViewHolder(convertView);
            convertView.setTag(vh);
        }
        else
        {

            vh = (DefaultViewHolder) convertView.getTag();

        }

        if(pokemon!=null)
        {


            vh.nombre.setText(pokemon.getNombre());
            if(pokemon.getTipo()[0].equals("Fuego"))
            {
                vh.nombre.setTextColor(Color.RED);
            }
            else if(pokemon.getTipo()[0].equals("Agua"))
            {
                vh.nombre.setTextColor(Color.BLUE);
            }
            else
            {
                vh.nombre.setTextColor(Color.GREEN);
            }
            vh.icono.setImageResource(pokemon.getIcono());
            //vh.icono.setImageResource(R.drawable.bullbasaur);
            vh.nombre.setTag(pokemon.hashCode());

        }
        return convertView;
    }
    @Override
    public Pokemon getItem(int position) {

        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
