package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.LogRecord;

/**
 * Created by Usuario on 30/01/2018.
 */

public class ConnectedThread  extends Thread{

    private BluetoothSocket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Handler mHandler;

    public ConnectedThread( BluetoothSocket socket, Handler mHandler)
    {
        this.socket=socket;
        this.mHandler=mHandler;
        try {
            this.inputStream=socket.getInputStream();
            this.outputStream=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        byte[] buffer = new byte[1024];  // capacidad de buffer para el stream
        int bytes; // bytes devueltos por .read()

        // Esperamos hasta que ocurra una excpcion
        while (true)
        {
            try {
                // Leemos del input
                bytes = inputStream.read(buffer);
                mHandler.obtainMessage(1, bytes, -1, buffer)
                        .sendToTarget();
            } catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }

    public void write(byte[] bytes) {
        try {
            outputStream.write(bytes);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public BluetoothSocket getSocket() {
        return socket;
    }
}
