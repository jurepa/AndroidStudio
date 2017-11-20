package com.example.pjarana.ej5bol7;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText numImgs;
    GridView grid;
    Button addImg;
    Button continuar;
    Button hacerFoto;
    ImageView imagen;
    Intent i;
    ArrayList<Bitmap>imagenesBitmap;
    ArrayList<ImageView>imagenes;
    MyAdapter<ImageView>gridAdapter;
    ArrayList<Uri>URIimagenes;
    AlertDialog.Builder builder;
    AlertDialog alerta;
    final int PICK_IMAGE=1;
    final int CAMERA_IMAGE=2;
    File cameraFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder=new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
        builder.setTitle("Error");
        builder.setMessage("El número de imágenes introducidas no corresponde con el número de imágenes que quiere en la galería");
        alerta=builder.create();
        //imagenesBitmap=new ArrayList<Bitmap>();
        URIimagenes=new ArrayList<Uri>();
        imagenes=new ArrayList<ImageView>();
        gridAdapter=new MyAdapter<>(getApplicationContext(),imagenes);
        numImgs=(EditText)findViewById(R.id.numeroImgs);
        hacerFoto=(Button)findViewById(R.id.hacerFoto);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(gridAdapter);
        addImg=(Button)findViewById(R.id.añadirImgs);
        continuar=(Button)findViewById(R.id.btnContinuar);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((Integer.parseInt(numImgs.getText().toString())!=imagenes.size())||numImgs.getText().toString()=="")
                {
                    alerta.show();
                }
                else
                {
                    i=new Intent(getApplicationContext(),Viewpager.class);
                    //i.putParcelableArrayListExtra("imagenes",imagenes);
                    i.putParcelableArrayListExtra("imagenes",URIimagenes);
                    startActivity(i);
                }
            }
        });
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, PICK_IMAGE);
            }
        });
        hacerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                /*cameraFile = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                i.putExtra(MediaStore.ACTION_IMAGE_CAPTURE, Uri.fromFile(cameraFile));*/
                startActivityForResult(i, CAMERA_IMAGE);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == PICK_IMAGE&& resultCode==RESULT_OK)
        {
            Uri selectedImg=data.getData();
            imagen=new ImageView(getApplicationContext());
            imagen.setImageURI(selectedImg);
            URIimagenes.add(selectedImg);
            imagenes.add(imagen);
            gridAdapter.notifyDataSetChanged();
        }
        else if(requestCode==CAMERA_IMAGE &&resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Uri uri=Uri.fromFile(cameraFile);
            URIimagenes.add(uri);
            imagen=new ImageView(getApplicationContext());
            imagen.setImageBitmap(imageBitmap);
            imagenes.add(imagen);
            gridAdapter.notifyDataSetChanged();
        }
    }


    /*public Uri bitmapToUriConverter(Bitmap mBitmap) {
        Uri uri = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            // Calculate inSampleSize
            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            Bitmap newBitmap = Bitmap.createScaledBitmap(mBitmap, 200, 200,
                    true);
            File file = new File(getApplication().getFilesDir(), "Image"
                    + new Random().nextInt() + ".jpeg");
            FileOutputStream out = getApplication().openFileOutput(file.getName(),
                    Context.MODE_WORLD_READABLE);
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //get absolute path
            String realPath = file.getAbsolutePath();
            File f = new File(realPath);
            uri = Uri.fromFile(f);

        } catch (Exception e) {

        }
        return uri;
    }*/


}
