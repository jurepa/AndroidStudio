package com.iesnervion.pjarana.neverstopclicking;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class StatsActivity extends AppCompatActivity {

    TextView resultado;
    TextView clicks;
    TextView clicksRival;
    TextView clicksPorSegundo;
    TextView fecha;
    SharedPreferences fichero;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        MobileAds.initialize(this,"ca-app-pub-4311390522883133~2229208522");

        fichero=getSharedPreferences("Estadisticas", Context.MODE_PRIVATE);
        resultado=(TextView)findViewById(R.id.resultado);
        clicks=(TextView)findViewById(R.id.clicks);
        clicksRival=(TextView)findViewById(R.id.clicksAdversario);
        clicksPorSegundo=(TextView)findViewById(R.id.clicksPorSegundo);
        fecha=(TextView)findViewById(R.id.fecha);
        resultado.setText(resultado.getText().toString()+" "+fichero.getString("estado","No has jugado todavía"));
        clicks.setText(clicks.getText().toString()+" "+String.valueOf(fichero.getInt("clicks",0)));
        clicksRival.setText(clicksRival.getText().toString()+" "+String.valueOf(fichero.getInt("clicksAdversario",0)));
        clicksPorSegundo.setText(clicksPorSegundo.getText().toString()+" "+String.valueOf(fichero.getFloat("clicksPorSegundo",0)));
        fecha.setText(fecha.getText().toString()+" "+fichero.getString("fecha","No has jugado todavía"));
    }
}
