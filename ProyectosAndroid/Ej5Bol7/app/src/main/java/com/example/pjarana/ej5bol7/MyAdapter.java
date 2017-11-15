package com.example.pjarana.ej5bol7;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Usuario on 31/10/2017.
 */

public class MyAdapter<T> extends BaseAdapter
{
    private Context c;
    private ArrayList<ImageView> items;



    public <T> MyAdapter(Context c, ArrayList<ImageView>items)
    {
        this.items=items;
        this.c=c;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        ImageView imagen=(ImageView)getItem(pos);
        return imagen;
    }
    @Override
    public ImageView getItem(int position) {

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
