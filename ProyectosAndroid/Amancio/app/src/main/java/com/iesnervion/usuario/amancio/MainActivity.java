package com.iesnervion.usuario.amancio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); Esto cambia la pantalla de orientación: Portrait en vertical y Landscape horizontal
        //Se puede cambiar también en el xml de AndroidManifest dentro de la etiqueta activity android:screenOrientation="landscape"
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToLogin (View v)
    {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }
}
