package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Usuario on 31/10/2017.
 */

public class MyAdapter<T> extends ArrayAdapter
{
    private Context c;
    private ArrayList<ProductoAComprar> items;

    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<T> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        DefaultViewHolder vh=null;

        ProductoAComprar producto=(ProductoAComprar) getItem(pos);
        if(convertView==null)
        {
            LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.listview_style, parent, false);
            vh=new DefaultViewHolder(convertView);
            convertView.setTag(vh);
        }
        else
        {

            vh = (DefaultViewHolder) convertView.getTag();

        }

        if(producto!=null) {
            vh.getCategoria().setText(producto.getCategoria());
            vh.getNombre().setText(producto.getNombre());
            vh.getColor().setText(producto.getColor());
            vh.getTalla().setText(producto.getTalla());
            vh.getPrecio().setText(producto.getPrecio());
        }
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
