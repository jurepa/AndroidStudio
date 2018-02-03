package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.nio.ByteBuffer;

public class GameActivity extends AppCompatActivity {

    ConnectedThread gestoraConexion;
    ConnectThread conexion;
    BluetoothDevice dispositivoAConectar;
    int clicks;
    boolean isChronoRunning;
    Button buttonClick;
    Chronometer chrono;
    byte[]bytes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        clicks=0;
        isChronoRunning=false;
        chrono=(Chronometer)findViewById(R.id.chronometer);
        chrono.setBase(5);
        chrono.setCountDown(true);
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(chrono.getText()=="00:00")
                {
                    chrono.stop();
                    buttonClick.setClickable(false);
                    //Instanciamos gestora conexion y escribimos los clicks para enviarlos al móvil de destino
                }
            }
        });
        buttonClick=(Button)findViewById(R.id.buttonClicks);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isChronoRunning)
                {
                    chrono.start();
                    isChronoRunning=true;
                }
                clicks++;

            }
        });
        dispositivoAConectar=getIntent().getParcelableExtra("dispositivoAConectar");
        conexion=new ConnectThread(dispositivoAConectar, BluetoothAdapter.getDefaultAdapter());
        conexion.run();
        //bytes= ByteBuffer.allocate(4).putInt(clicks).array();
        gestoraConexion=new ConnectedThread(conexion.getSocket());

    }
}
