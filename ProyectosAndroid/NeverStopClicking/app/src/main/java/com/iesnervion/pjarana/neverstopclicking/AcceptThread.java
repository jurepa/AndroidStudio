package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

/**
 * Created by pjarana on 12/02/18.
 */

public class AcceptThread extends Thread {
    private static final String APPNAME="NEVERSTOPCLICKING";
    private BluetoothServerSocket serverSocket;
    private BluetoothSocket btSocket;

    public AcceptThread(BluetoothAdapter mBluetoothAdapter)
    {
        try
        {
            //Crea un socket para que otros dispositivos se puedan conectar a él,
            //cualquier dispositivo remoto que se conecte será autenticado y la comunicación en el socket será encriptada
            serverSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(APPNAME, JoinGame.IDENTIFIER);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //Procedemos a esperar conexiones
    public void run()
    {
        boolean waiting=true;
        this.btSocket=null; //Este será el punto de conexión entre el cliente y nosotros

        while(waiting)
        { //Mientras no reciba conexiones
            try
            {
                this.btSocket = serverSocket.accept(); //Esperamos a que un dispositivo se nos conecte
                //accept(); lo que hace es permitir conexiones entrantes

            } catch (IOException e)
            {
                waiting=false;
                e.printStackTrace();

            }
            if(this.btSocket!=null)
            {
                try
                {
                    serverSocket.close(); //Si se nos ha conectado un dispositivo, cerramos el serverSocket
                    //Podremos seguir tratanto con el Dispositivo remoto aunque cerremos el serverSocket
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }
    public void cancel()
    {
        try
        {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BluetoothServerSocket getServerSocket() {
        return serverSocket;
    }

    public BluetoothSocket getBtSocket() {
        return btSocket;
    }
}
