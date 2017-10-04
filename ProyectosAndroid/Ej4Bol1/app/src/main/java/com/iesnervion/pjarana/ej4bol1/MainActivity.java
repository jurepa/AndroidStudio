package com.iesnervion.pjarana.ej4bol1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pasarFoto(View v)
    {
        ImageView imagen=(ImageView)findViewById(R.id.foto);
        Button anterior=(Button)findViewById(R.id.anterior);
        Button siguiente=(Button)findViewById(R.id.siguiente);

        int[]fotoID={
                R.drawable.nas,
                R.drawable.maxresdefault,
                R.drawable.snoop_dogg_photo_by_estevan_oriol_archive_photos_getty_455616412
        };
        switch (v.getId())
        {
            case R.id.siguiente:
                i++;
                if(i==fotoID.length)
                {
                    i=0;
                }
                imagen.setImageResource(fotoID[i]);
                break;
            case R.id.anterior:
                i--;
                if(i<0)
                {
                    i=fotoID.length-1;
                }
                imagen.setImageResource(fotoID[i]);
                break;
        }
    }
}
