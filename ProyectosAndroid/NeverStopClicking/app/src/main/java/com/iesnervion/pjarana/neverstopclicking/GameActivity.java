package com.iesnervion.pjarana.neverstopclicking;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
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
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import xyz.hanks.library.bang.SmallBangView;

/*
ANOTACIONES:

-Se queda pillado al enviar los datos (SOLUCIONADO)
-Hay veces que no encuentra dispositivos nuevos, poner cartelito al principio que es mejor vincularlos antes (SOLUCIONADO)
 */
public class GameActivity extends AppCompatActivity {

    ConnectedThread gestoraConexion;
    ConnectThread conexion;
    BluetoothDevice dispositivoAConectar;
    SharedPreferences estadisticas;
    SharedPreferences.Editor editor;
    TextView txtClicks;
    TextView txtClicksRival;
    MediaPlayer disparo;
    int clicks;
    int clicksAdversario;
    boolean isChronoRunning;
    boolean heHechoClick;
    SmallBangView buttonClick;
    Chronometer chrono;
    Handler mHandler;
    byte[]bytes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        disparo=MediaPlayer.create(this,R.raw.disparo);
        estadisticas=getSharedPreferences("Estadisticas", Context.MODE_PRIVATE);

        mHandler=new Handler()
        {
            @Override
            public void handleMessage(Message inputMessage)
            {
                byte[] write = (byte[]) inputMessage.obj;
                String res = new String(write, 0, inputMessage.arg1);
                String mensaje = res.trim();
                clicksAdversario=Integer.parseInt(mensaje);
                txtClicksRival.setText("Clicks Rival: "+String.valueOf(clicksAdversario));
                if (!isChronoRunning&&heHechoClick) {
                    editor=estadisticas.edit();
                    final AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    if (clicksAdversario > clicks) {
                        builder.setMessage("Vaya parguela, has perdido, tu oponente ha hecho " + String.valueOf(clicksAdversario));
                        builder.setTitle("Derrota...");
                        editor.putString("estado","Derrota");
                    } else if (clicksAdversario == clicks) {
                        builder.setMessage("Lol, habéis empatado, tu oponente ha hecho " + String.valueOf(clicksAdversario) + " clicks");
                        builder.setTitle("Empate");
                        editor.putString("estado","Empate");
                    } else {
                        builder.setMessage("Has ganado, vaya crack, clicks del oponente: " + String.valueOf(clicksAdversario));
                        builder.setTitle("Victoria!!!");
                        editor.putString("estado","Victoria");
                    }

                    builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    });

                    editor.putString("fecha", new SimpleDateFormat("yyyy:MM:dd - hh:mm:ss a").format(Calendar.getInstance().getTime()));
                    editor.putInt("clicks",clicks);
                    editor.putInt("clicksAdversario",clicksAdversario);
                    editor.putInt("clicksPorSegundo",clicks/5);
                    editor.commit();
                    builder.create().show();
                }
            }

        };
        clicks=0;
        heHechoClick=false;
        isChronoRunning=false;
        dispositivoAConectar=getIntent().getParcelableExtra("dispositivoAConectar");
        ConnectionBT conexion=new ConnectionBT();
        conexion.execute();

        txtClicksRival=(TextView)findViewById(R.id.clicksRival);
        txtClicks=(TextView)findViewById(R.id.txtClicks);
        chrono=(Chronometer)findViewById(R.id.chronometer);
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(chrono.getText().toString().equalsIgnoreCase("00:05"))
                {
                    chrono.stop();
                    buttonClick.setClickable(false);
                    isChronoRunning=false;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disparo.release();
    }

    private class ConnectionBT extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            conexion=new ConnectThread(dispositivoAConectar, BluetoothAdapter.getDefaultAdapter());
            conexion.run();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            gestoraConexion=new ConnectedThread(conexion.getSocket(),mHandler);
            gestoraConexion.start();
        }
    }
}
