package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Set;

public class LocalGame extends AppCompatActivity {

    TextView txt;
    BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        txt=(TextView)findViewById(R.id.listaDispositivos);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        txt.append("\n Empezamos a buscar");
        bluetoothAdapter.startDiscovery();
        txt.append("\n Terminamos de buscar");
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : devices) {
            txt.append("\nYa vinculado: " + device.getName() + " MAC: " + device.getAddress());
        }
        BroadcastReceiver br=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction()==BluetoothDevice.ACTION_FOUND)
                {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    txt.append("Nuevo: "+device.getName()+" MAC: "+device.getAddress());
                }
            }
        };
    }

}
