package com.iesnervion.pjarana.neverstopclicking;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class LocalGame extends AppCompatActivity {

    ListView lista;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String>listaDispositivos;
    ArrayAdapter<String> adapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        lista=(ListView)findViewById(R.id.listaDispositivos);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listaDispositivos=new ArrayList<String>();
        adapter.addAll(listaDispositivos);
        lista.setAdapter(adapter);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter.isDiscovering())
        {
            bluetoothAdapter.cancelDiscovery();
        }
        else
        {
            bluetoothAdapter.startDiscovery();
            progressBar.setVisibility(View.VISIBLE);
            IntentFilter intentFilter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(br,intentFilter);
        }
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : devices) {
            adapter.add(device.getName());
            adapter.notifyDataSetChanged();
        }

    }
    private final BroadcastReceiver br=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction()==BluetoothDevice.ACTION_FOUND)
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LocalGame.this.adapter.add(device.getName());
                adapter.notifyDataSetChanged();
            }
        }
    };

}
