package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Usuario on 30/01/2018.
 */

public class ConnectedThread  extends Thread{

    private BluetoothSocket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ConnectedThread( BluetoothSocket socket)
    {
        this.socket=socket;
        try {
            this.inputStream=socket.getInputStream();
            this.outputStream=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
