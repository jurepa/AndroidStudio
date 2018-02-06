package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by pjarana on 31/01/18.
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
        this.btSocket=null; //Este será el punto de conexión entre el cliente y nosotros

        while(this.btSocket==null) { //Mientras no reciba conexiones
            try
            {
                this.btSocket = serverSocket.accept(); //Esperamos a que un dispositivo se nos conecte
                                                //accept(); lo que hace es permitir conexiones entrantes
            } catch (IOException e)
            {
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
