package com.example.pjarana.ej4bol7;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[]localizaciones={"","Sevilla","Paris","New York","Berlin","Bali","Madagascar","Tokyo","Vancouver"};
    ArrayAdapter<String>spinnerAdapter;
    Spinner spinner;
    TextView texto;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto=(TextView)findViewById(R.id.texto);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinnerAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,R.id.customSpinnerItemTextView,localizaciones);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ciudadElegida=spinner.getAdapter().getItem(position).toString();
                switch (ciudadElegida)
                {
                    case "Sevilla":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Sevilla"));
                        startActivity(i);
                        break;
                    case "Paris":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,  0?q=Paris, Francia"));
                        startActivity(i);
                        break;
                    case "New York":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=New York, Estados Unidos"));
                        startActivity(i);
                        break;
                    case "Berlin":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Berlin, Alemania"));
                        startActivity(i);
                        break;
                    case "Madagascar":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Madagascar"));
                        startActivity(i);
                        break;
                    case "Bali":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Bali"));
                        startActivity(i);
                        break;
                    case "Tokyo":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Tokyo, Japón"));
                        startActivity(i);
                        break;
                    case "Vancouver":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Vancouver,Canadá"));
                        startActivity(i);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                texto.setTextColor(Color.RED);
            }
        });
    }
}
