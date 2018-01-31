package com.iesnervion.pjarana.neverstopclicking;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;

/**
 * Created by Usuario on 31/01/2018.
 */

public class GestoraServicios extends Application{
    private AcceptThread servidor;
    private ConnectThread cliente;
    private ConnectedThread gestoraConexiones;

}
