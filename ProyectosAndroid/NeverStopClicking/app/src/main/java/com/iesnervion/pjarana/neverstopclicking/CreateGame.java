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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import xyz.hanks.library.bang.SmallBangView;

/*
ANOTACIONES:

-Se queda pillado al enviar los datos (SOLUCIONADO)
-Hay veces que no encuentra dispositivos nuevos, poner cartelito al principio que es mejor vincularlos antes (SOLUCIONADO)
 */
public class CreateGame extends AppCompatActivity {

    private final int MYBTISDISCOVERABLE=1;
    ConnectedThread gestoraConexion;
    BluetoothDevice dispositivoAConectar;
    Dialog dialog;
    TextView txtClicks;
    TextView txtClicksRival;
    SharedPreferences estadisticas;
    SharedPreferences.Editor editor;
    int clicks;
    int clicksAdversario;
    boolean isChronoRunning;
    SmallBangView buttonClick;
    Chronometer chrono;
    Handler mHandler;
    MediaPlayer disparo;
    boolean heHechoClick;
    boolean rivalHaAcabado;
    final String mensajeFinalizacion="s";
    AcceptThread aceptarConexiones;
    Button btnCancelar;
    boolean heAcabado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        estadisticas=getSharedPreferences("Estadisticas", Context.MODE_PRIVATE);
        ConnectionBT conexion=new ConnectionBT();
        conexion.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
                if(Character.isDigit(mensaje.charAt(0)))
                {
                    clicksAdversario=Integer.parseInt(mensaje);
                    txtClicksRival.setText("Clicks Rival: "+String.valueOf(clicksAdversario));
                }
                else
                {
                    rivalHaAcabado=true;
                }
                /*if(!isChronoRunning&&heHechoClick&&rivalHaAcabado) {
                    editor=estadisticas.edit();
                    final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGame.this);
                    if (clicksAdversario > clicks) {
                        builder.setMessage("Vaya parguela, has perdido, tu oponente ha hecho " + String.valueOf(clicksAdversario) + " clicks");
                        builder.setTitle("Derrota...");
                        editor.putString("estado","Derrota");
                    } else if (clicksAdversario == clicks) {
                        builder.setMessage("Lol, habéis empatado, tu oponente ha hecho " + String.valueOf(clicksAdversario) + " clicks");
                        builder.setTitle("Empate");
                        editor.putString("estado","Empate");
                    } else {
                        builder.setMessage("Has ganado, vaya crack, clicks del oponente: " + String.valueOf(clicksAdversario));
                        builder.setTitle("Victoria!!");
                        editor.putString("estado","Victoria");
                    }
                    builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    editor.putString("fecha", new SimpleDateFormat("yyyy/MM/dd - hh:mm:ss a").format(Calendar.getInstance().getTime()));
                    editor.putInt("clicks",clicks);
                    editor.putInt("clicksAdversario",clicksAdversario);
                    editor.putFloat("clicksPorSegundo",Float.parseFloat(String.valueOf(clicks))/5);
                    editor.commit();
                    builder.create().show();
                }*/

            }

        };
        clicks=0;
        heAcabado=false;
        heHechoClick=false;
        isChronoRunning=false;
        disparo=MediaPlayer.create(this,R.raw.disparo);
        dispositivoAConectar=getIntent().getParcelableExtra("dispositivoAConectar");
        txtClicksRival=(TextView)findViewById(R.id.clicksRival);
        txtClicks=(TextView)findViewById(R.id.txtClicks);
        chrono=(Chronometer)findViewById(R.id.chronometer);

        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(chrono.getText().toString().equalsIgnoreCase("00:05"))
                {
                    isChronoRunning=false;
                    heAcabado=true;
                    chrono.stop();
                    buttonClick.setClickable(false);
                    //Instanciamos gestora conexion y escribimos los clicks para enviarlos al móvil de destino
                    gestoraConexion.write(mensajeFinalizacion.getBytes());
                    gestoraConexion.write(String.valueOf(clicks).getBytes());

                }
            }
        });
        buttonClick=findViewById(R.id.buttonClicks);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heHechoClick=true;
                if (!isChronoRunning) {
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    isChronoRunning = true;

                }
                disparo.start();
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
                gestoraConexion.write(String.valueOf(clicks).getBytes());

            }
        });



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

    private class ConnectionBT extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
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
            quitarProgressDialog();
            gestoraConexion=new ConnectedThread(aceptarConexiones.getBtSocket(),mHandler);
            gestoraConexion.start();
            comprobarGanador prueba=new comprobarGanador();
            prueba.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
    private class comprobarGanador extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            while(!heAcabado&&!rivalHaAcabado);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            editor=estadisticas.edit();
            final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGame.this);
            if (clicksAdversario > clicks) {
                builder.setMessage("Vaya parguela, has perdido, tu oponente ha hecho " + String.valueOf(clicksAdversario) + " clicks");
                builder.setTitle("Derrota...");
                editor.putString("estado","Derrota");
            } else if (clicksAdversario == clicks) {
                builder.setMessage("Lol, habéis empatado, tu oponente ha hecho " + String.valueOf(clicksAdversario) + " clicks");
                builder.setTitle("Empate");
                editor.putString("estado","Empate");
            } else {
                builder.setMessage("Has ganado, vaya crack, clicks del oponente: " + String.valueOf(clicksAdversario));
                builder.setTitle("Victoria!!");
                editor.putString("estado","Victoria");
            }
            builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            editor.putString("fecha", new SimpleDateFormat("yyyy/MM/dd - hh:mm:ss a").format(Calendar.getInstance().getTime()));
            editor.putInt("clicks",clicks);
            editor.putInt("clicksAdversario",clicksAdversario);
            editor.putFloat("clicksPorSegundo",Float.parseFloat(String.valueOf(clicks))/5);
            editor.commit();
            builder.create().show();
        }
    }


}
