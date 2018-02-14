package com.iesnervion.pjarana.neverstopclicking;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooseGameType extends AppCompatActivity {

    Button btnLocal;
    Button btnUnirse;
    Button btnVolver;
    BluetoothAdapter bluetoothAdapter;
    final int REQUEST_ENABLE_BT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type_game);
        btnLocal=(Button)findViewById(R.id.btnLocal);
        btnUnirse=(Button)findViewById(R.id.btnUnirse);
        btnVolver=(Button)findViewById(R.id.btnVolver);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),"Tu móvil no soporta bluetooth",Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            if (!bluetoothAdapter.isEnabled())
            {
                Intent activarBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(activarBluetooth, REQUEST_ENABLE_BT);
            }
        }
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),CreateGame.class);
                startActivity(i);
            }
        });
        btnUnirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),JoinGame.class);
                startActivity(i);
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
                this.finish();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

/*    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    */
}
