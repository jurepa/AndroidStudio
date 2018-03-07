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
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    Button btnSalir;
    Button btnEstadisticas;
    Button btnJugar;
    MediaPlayer musicTetris;
    AlertDialog.Builder builder;
    InterstitialAd mInterstitialAd; //Anuncio de pantalla completa
    InterstitialAd mInterstitialAdForButton;
    AdRequest adRequest; //Petición a GoogleServices para mostrar el anuncio
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd=new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4311390522883133/8516017910");
        mInterstitialAdForButton=new InterstitialAd(this);
        mInterstitialAdForButton.setAdUnitId("ca-app-pub-4311390522883133/8516017910");
        mInterstitialAdForButton.loadAd(new AdRequest.Builder().build());
        adRequest=new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        musicTetris=MediaPlayer.create(this,R.raw.tetris);
        musicTetris.start();
        musicTetris.setLooping(true);
        btnEstadisticas=(Button)findViewById(R.id.btnOpciones);
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
        btnEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAdForButton.isLoaded()) //Si está cargado lo mostramos
                {
                    mInterstitialAdForButton.show();
                }
                Intent i=new Intent(getApplicationContext(),StatsActivity.class);
                startActivity(i);

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
        mInterstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show(); //Cuando se cargue lo mostramos

            }

            /*@Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(),"Algo ha fallado: "+errorCode,Toast.LENGTH_LONG).show();
            }*/

        });
        mInterstitialAdForButton.setAdListener(new AdListener()
        {
            @Override
            public void onAdClosed() {
                mInterstitialAdForButton.loadAd(new AdRequest.Builder().build());
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
