package com.iesnervion.pjarana.neverstopclicking;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSalir;
    Button btnJugar;
    final int REQUEST_ENABLE_BT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer musicTetris=MediaPlayer.create(this,R.raw.tetris);
        musicTetris.start();
        btnSalir=findViewById(R.id.btnSalir);
        btnJugar=findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter == null) {
                    Toast.makeText(getApplicationContext(),"Tu móvil no soporta bluetooth",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (!bluetoothAdapter.isEnabled())
                    {
                        Intent activarBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(activarBluetooth, REQUEST_ENABLE_BT);
                    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                    /*else
                    {
                        bluetoothAdapter.disable();
                        Toast.makeText(getApplicationContext(),"Se ha desactivado el bluetooth",Toast.LENGTH_LONG).show();
                    }*/
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
                }
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Seguro que quieres salir?");
                builder.setTitle("Salir");
                builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("Seguir jugando", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_ENABLE_BT)
        {
            if (resultCode == RESULT_OK)
            {
                Toast.makeText(getApplicationContext(),"Se ha activado el bluetooth",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"El bluetooth no se ha activado",Toast.LENGTH_LONG).show();
            }
        }
    }

}
