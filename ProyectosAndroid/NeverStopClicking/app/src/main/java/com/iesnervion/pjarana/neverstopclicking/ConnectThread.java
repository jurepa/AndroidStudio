package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by pjarana on 31/01/18.
 */

public class ConnectThread extends Thread {
    private BluetoothSocket socket;
    private BluetoothDevice dispositivoRemoto;
    private BluetoothAdapter mBluetoothAdapter;

    public ConnectThread(BluetoothDevice dispositivoRemoto, BluetoothAdapter miDispositivo)
    {
        this.mBluetoothAdapter=miDispositivo;
        try {
            this.socket = dispositivoRemoto.createRfcommSocketToServiceRecord(JoinGame.IDENTIFIER);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        this.mBluetoothAdapter.cancelDiscovery();

        try
        {
            socket.connect();
        } catch (IOException e)
        {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        //Aquí hacemos los que sea, manageConnection

    }

}
