package com.iesnervion.usuario.ejintentfilter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton=(Button)findViewById(R.id.btn);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction("camara");
                i.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(Intent.createChooser(i,"Elige, pero vas a ser trolleado"));
            }
        });
    }
}
