package com.example.pjarana.email;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText receptor;
    EditText message;
    Button btnEnviar;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receptor=(EditText)findViewById(R.id.receptor);
        message=(EditText)findViewById(R.id.message);
        btnEnviar=(Button)findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL,new String[]{receptor.getText().toString()});
                i.putExtra(Intent.EXTRA_TEXT,message.getText().toString());
                i.setType("message/rfc82");
                startActivity(i);
            }
        });
    }
}
