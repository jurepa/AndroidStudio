package com.iesnervion.pjarana.neverstopclicking;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class JoinGame extends AppCompatActivity {

    ListView lista;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String>nombresDispositivos;
    ArrayAdapter<String> adapter;
    ArrayList<BluetoothDevice>listaDispositivos;
    ProgressBar progressBar;
    BluetoothSocket socket;
    BluetoothDevice dispositivoAConectar;
    static final UUID IDENTIFIER=UUID.fromString("cda489c4-328c-45d5-80bf-240ff74f4277");
    private final int MYBTISDISCOVERABLE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        lista=(ListView)findViewById(R.id.listaDispositivos);
        lista.setClickable(false);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listaDispositivos=new ArrayList<BluetoothDevice>();
        nombresDispositivos=new ArrayList<String>();
        adapter.addAll(nombresDispositivos);
        lista.setAdapter(adapter);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter.getScanMode()!=BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) //Si el móvil no es visible, hacemos una petición al usuario para ponerlo visible
        {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivityForResult(intent, MYBTISDISCOVERABLE);
        }
        if(bluetoothAdapter.isDiscovering()) //Si esta escaneando dispositivos, cancelamos
        {
            bluetoothAdapter.cancelDiscovery();
        }
        bluetoothAdapter.startDiscovery();//Empezamos el escaneo de dispositivos
        progressBar.setVisibility(View.VISIBLE);
        IntentFilter intentFilter1=new IntentFilter(BluetoothDevice.ACTION_FOUND); //Cuando encuentre un dispositivo, que entre al BroadcastReceiver
        IntentFilter intentFilter2=new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);//Cuando termine de escanear, que entre al BroadCastReceiver
        registerReceiver(br,intentFilter1);
        registerReceiver(br,intentFilter2);
        final Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices(); //Nos devuelve un set de los dispositivos ya vinculados
        for (BluetoothDevice device : devices) { //Los añadimos a la lista
            adapter.add(device.getName());
            adapter.notifyDataSetChanged();
            listaDispositivos.add(device);
        }


    }
    private final BroadcastReceiver br=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction()==BluetoothDevice.ACTION_FOUND) //Si ha encontrado un dispositivo, lo añadimos a la lista
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE); //Así obtenemos el Bluetooth device
                JoinGame.this.adapter.add(device.getName());
                JoinGame.this.listaDispositivos.add(device);
                adapter.notifyDataSetChanged();
            }
            if(intent.getAction()==BluetoothAdapter.ACTION_DISCOVERY_FINISHED) //Si ha terminado de escanear quitamos la progressbar
            {
                progressBar.setVisibility(View.INVISIBLE);
                lista.setClickable(true);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        ConnectThread conexion;
                        boolean encontrado=false;
                        String nombreDispositivo= adapter.getItem(i);
                        for (int j=0;j<listaDispositivos.size()||!encontrado;j++) //Buscamos el dispositivo con el nombre seleccionado
                        {
                            if(nombreDispositivo.equals(listaDispositivos.get(j).getName()))
                            {
                                dispositivoAConectar=listaDispositivos.get(j);
                                encontrado=true;
                            }
                        }
                        conexion=new ConnectThread(dispositivoAConectar,bluetoothAdapter);
                        conexion.run();

                        /*new Thread() //Iniciamos una tarea en segundo plano para no bloquear la UI
                        {
                            public void run()
                            {
                                boolean fallo=false;
                                try
                                {
                                    socket=createBluetoothSocket(dispositivoAConectar); //Creamos el socket, que va a ser el dispositivo a conectarnos
                                }catch(IOException e)
                                {
                                    fallo=true;
                                    Toast.makeText(getApplicationContext(),"La creación del Socket ha fallado(Línea 100, JoinGame)",Toast.LENGTH_LONG).show();
                                }
                                try
                                {
                                    socket.connect(); //Establecemos la conexion
                                }catch(IOException e)
                                {
                                    fallo=true;
                                }finally //Si hay problemas, la cerramos
                                {
                                    try {
                                        socket.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(!fallo)
                                {
                                    ConnectedThread connectedThread=new ConnectedThread(socket);
                                }
                            }
                        }.start();*/
                    }
                });
            }
        }
    };
    @Override
    protected void onDestroy() { //Recomendación de AndroidDevelopers
        super.onDestroy();
        unregisterReceiver(br);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==MYBTISDISCOVERABLE)
        {
            Toast.makeText(this,"Tu teléfono ahora es visible",Toast.LENGTH_LONG).show();
        }
    }

    /*private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException { //Crea el socket
        BluetoothSocket socketCreado;
        try {

            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, identifier);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  device.createRfcommSocketToServiceRecord(identifier);
    }*/
}
