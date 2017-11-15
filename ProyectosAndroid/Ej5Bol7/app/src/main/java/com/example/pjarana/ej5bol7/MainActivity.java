package com.example.pjarana.ej5bol7;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText numImgs;
    GridView grid;
    Button addImg;
    Button continuar;
    Button hacerFoto;
    Intent i;
    ArrayList<ImageView>imagenes;
    MyAdapter<ImageView>gridAdapter;
    private static final int PICK_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagenes=new ArrayList<ImageView>();
        gridAdapter=new MyAdapter<>(getApplicationContext(),imagenes);
        numImgs=(EditText)findViewById(R.id.numeroImgs);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(gridAdapter);
        addImg=(Button)findViewById(R.id.añadirImgs);
        continuar=(Button)findViewById(R.id.btnContinuar);
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, PICK_IMAGE);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        ImageView imagen=null;
        if (requestCode == PICK_IMAGE&& resultCode==RESULT_OK)
        {
            Uri selectedImg=data.getData();
            imagen=new ImageView(getApplicationContext());
            imagen.setImageURI(selectedImg);
            imagenes.add(imagen);
            gridAdapter.notifyDataSetChanged();
        }
    }
}
