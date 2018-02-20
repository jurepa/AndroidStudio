package com.iesnervion.pjarana.pruebaantesexamen.CustomListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iesnervion.pjarana.pruebaantesexamen.DAO.Usuario;
import com.iesnervion.pjarana.pruebaantesexamen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 31/10/2017.
 */

public class MyAdapter<T> extends ArrayAdapter
{
    private int idFila;
    public <T> MyAdapter(Context c, int resID, List<T> objects)
    {
        super(c,resID,objects);
        this.idFila=resID;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        DefaultViewHolder vh=null;
        Usuario u=(Usuario) getItem(pos);
        if(convertView==null)
        {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.row_style, parent, false);
            vh=new DefaultViewHolder(convertView);
            convertView.setTag(vh);
        }
        else
        {
            vh =(DefaultViewHolder) convertView.getTag();
        }

        if(u!=null)
        {
            vh.getIdPersona().setText(vh.getIdPersona().getText().toString()+""+u.getId());
            vh.getNombreUsuario().setText(vh.getNombreUsuario().getText().toString()+""+u.getNombre());
        }
        return convertView;
    }
}
