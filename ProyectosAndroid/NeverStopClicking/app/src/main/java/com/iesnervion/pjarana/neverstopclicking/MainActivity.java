package com.iesnervion.pjarana.neverstopclicking;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSalir;
    Button btnJugar;
    MediaPlayer musicTetris;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicTetris=MediaPlayer.create(this,R.raw.tetris);
        musicTetris.start();
        musicTetris.setLooping(true);
        btnSalir=findViewById(R.id.btnSalir);
        btnJugar=findViewById(R.id.btnJugar);
        builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("¿Seguro que quieres salir?");
        builder.setTitle("Salir");
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(BluetoothAdapter.getDefaultAdapter().isEnabled())
                {
                    BluetoothAdapter.getDefaultAdapter().disable();
                }
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Seguir jugando", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                    Intent i=new Intent(getApplicationContext(),ChooseGameType.class);
                    startActivity(i);
                    /*else
                    {
                        bluetoothAdapter.disable();
                        Toast.makeText(getApplicationContext(),"Se ha desactivado el bluetooth",Toast.LENGTH_LONG).show();
                    }*/
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.create().show();
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            builder.create().show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicTetris.release();
    }
}
