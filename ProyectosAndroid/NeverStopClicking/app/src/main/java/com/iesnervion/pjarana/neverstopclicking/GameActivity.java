package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    ConnectedThread gestoraConexion;
    ConnectThread conexion;
    BluetoothDevice dispositivoAConectar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        dispositivoAConectar=getIntent().getParcelableExtra("dispositivoAConectar");
        conexion=new ConnectThread(dispositivoAConectar, BluetoothAdapter.getDefaultAdapter());
        conexion.run(getApplication());
        gestoraConexion=new ConnectedThread(conexion.getSocket());

    }
}
