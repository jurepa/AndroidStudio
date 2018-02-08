package com.iesnervion.pjarana.neverstopclicking;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.nio.ByteBuffer;

import xyz.hanks.library.bang.SmallBangView;

public class CreateGame extends AppCompatActivity {

    private final int MYBTISDISCOVERABLE=1;
    ConnectedThread gestoraConexion;
    BluetoothDevice dispositivoAConectar;
    Dialog dialog;
    TextView txtClicks;
    int clicks;
    int clicksAdversario;
    boolean isChronoRunning;
    SmallBangView buttonClick;
    Chronometer chrono;
    Handler mHandler;
    byte[]bytes;
    AcceptThread aceptarConexiones;
    Button btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        if(BluetoothAdapter.getDefaultAdapter().getScanMode()!=BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) //Si el móvil no es visible, hacemos una petición al usuario para ponerlo visible
        {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivityForResult(intent, MYBTISDISCOVERABLE);
        }
        mHandler=new Handler()
        {
            @Override
            public void handleMessage(Message inputMessage)
            {
                byte[] write = (byte[]) inputMessage.obj;
                String res = new String(write, 0, inputMessage.arg1);
                String mensaje = res.trim();
                clicksAdversario=Integer.parseInt(mensaje);
                    final AlertDialog.Builder builder=new AlertDialog.Builder(CreateGame.this);
                    if(clicksAdversario>clicks)
                    {
                        builder.setMessage("Vaya parguela, has perdido, tu oponente ha hecho "+String.valueOf(clicksAdversario)+" clicks");
                        builder.setTitle("Derrota...");
                    }
                    else if(clicksAdversario==clicks)
                    {
                        builder.setMessage("Lol, habéis empatado, tu oponente ha hecho "+String.valueOf(clicksAdversario)+" clicks");
                        builder.setTitle("Empate");
                    }
                    else
                    {
                        builder.setMessage("Has ganado, vaya crack, clicks del oponente: "+String.valueOf(clicksAdversario));
                        builder.setTitle("Victoria!!");
                    }
                    builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                            ChooseGameType.chooseGameType.finish();
                        }
                    });
                    builder.create().show();

            }

        };
        clicks=0;
        isChronoRunning=false;
        dispositivoAConectar=getIntent().getParcelableExtra("dispositivoAConectar");
        txtClicks=(TextView)findViewById(R.id.txtClicks);
        chrono=(Chronometer)findViewById(R.id.chronometer);
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(chrono.getText().toString().equalsIgnoreCase("00:05"))
                {
                    chrono.stop();
                    buttonClick.setClickable(false);
                    //Instanciamos gestora conexion y escribimos los clicks para enviarlos al móvil de destino

                    gestoraConexion=new ConnectedThread(aceptarConexiones.getBtSocket(),mHandler);
                    gestoraConexion.write(String.valueOf(clicks).getBytes());
                    gestoraConexion.start();

                }
            }
        });
        buttonClick=findViewById(R.id.buttonClicks);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isChronoRunning) {
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    isChronoRunning = true;

                }
                buttonClick.setSelected(true);
                buttonClick.likeAnimation(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        buttonClick.setSelected(false);
                    }
                });
                clicks++;
                txtClicks.setText("Clicks: " + String.valueOf(clicks));

            }
        });

        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mostrarProgressDialog();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                aceptarConexiones=new AcceptThread(BluetoothAdapter.getDefaultAdapter());
                aceptarConexiones.run();
                return  null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                quitarProgressDialog();
            }
        }.execute();

    }
    public void mostrarProgressDialog()
    {
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {*/
                dialog=new Dialog(CreateGame.this);
                dialog.setContentView(R.layout.customdialog);
                btnCancelar=dialog.findViewById(R.id.btnCancelar);
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        CreateGame.this.finish();
                    }
                });
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
           /*}
        });*/
    }
    public void quitarProgressDialog()
    {
       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {*/
                dialog.dismiss();
           /*}
        });*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==MYBTISDISCOVERABLE)
        {
            Toast.makeText(this,"Tu teléfono ahora es visible",Toast.LENGTH_LONG).show();
        }
        /*else
        {
            finish();
            Toast.makeText(this,"No puedes unirte a una partida si tu teléfono no es visible",Toast.LENGTH_LONG).show();
        }*/
    }
    /*
    Clase de conexion como servidor
     */
    private class AcceptThread {

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
}
