package com.iesnervion.pjarana.neverstopclicking;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
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
import xyz.hanks.library.bang.SmallBangView;

/*
ANOTACIONES:

-Se queda pillado al enviar los datos
-Hay veces que no encuentra dispositivos nuevos, poner cartelito al principio que es mejor vincularlos antes
 */
public class GameActivity extends AppCompatActivity {

    ConnectedThread gestoraConexion;
    ConnectThread conexion;
    BluetoothDevice dispositivoAConectar;
    ProgressDialog progressDialog;
    TextView txtClicks;
    int clicks;
    int clicksAdversario;
    boolean isChronoRunning;
    SmallBangView buttonClick;
    Chronometer chrono;
    Handler mHandler;
    byte[]bytes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mHandler=new Handler()
        {
            @Override
            public void handleMessage(Message inputMessage)
            {
                byte[] write = (byte[]) inputMessage.obj;
                String res = new String(write, 0, inputMessage.arg1);
                String mensaje = res.trim();
                clicksAdversario=Integer.parseInt(mensaje);
                    final AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                    if(clicksAdversario>clicks)
                    {
                        builder.setMessage("Vaya parguela, has perdido, tu oponente ha hecho "+String.valueOf(clicksAdversario));
                        builder.setTitle("Derrota...");
                    }
                    else if(clicksAdversario==clicks)
                    {
                        builder.setMessage("Lol, habéis empatado, tu oponente ha hecho "+String.valueOf(clicksAdversario));
                        builder.setTitle("Empate");
                    }
                    else
                    {
                        builder.setMessage("Has ganado, vaya crack, clicks del oponente: "+String.valueOf(clicksAdversario));
                        builder.setTitle("Victoria!!!");
                    }

                    builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    });
                    builder.create().show();
                    ChooseGameType.chooseGameType.finish();
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

                    gestoraConexion=new ConnectedThread(conexion.getSocket(),mHandler);
                    gestoraConexion.start();
                    gestoraConexion.write(String.valueOf(clicks).getBytes());
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
        conexion=new ConnectThread(dispositivoAConectar, BluetoothAdapter.getDefaultAdapter());
        conexion.start();
    }
}
