package com.iesnervion.pjarana.neverstopclicking;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.ByteBuffer;

import xyz.hanks.library.bang.SmallBangView;

public class CreateGame extends AppCompatActivity {

    private final int MYBTISDISCOVERABLE=1;
    ConnectedThread gestoraConexion;
    BluetoothDevice dispositivoAConectar;
    TextView txtClicks;
    int clicks;
    int clicksAdversario;
    boolean isChronoRunning;
    SmallBangView buttonClick;
    Chronometer chrono;
    Handler mHandler;
    byte[]bytes;
    AcceptThread aceptarConexiones;
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
        mHandler=new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message inputMessage)
            {
                clicksAdversario=ByteBuffer.wrap((byte[]) inputMessage.obj).getInt();
                if(inputMessage.what==1)
                {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(CreateGame.this);
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
                        builder.setTitle("Victoria!!");
                    }
                    builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    });
                    builder.create().show();
                }
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
                    gestoraConexion.write(ByteBuffer.allocate(1024).putInt(clicks).array());
                    gestoraConexion.run();

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
        aceptarConexiones=new AcceptThread(BluetoothAdapter.getDefaultAdapter());
        aceptarConexiones.run();

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
}
