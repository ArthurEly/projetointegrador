package br.com.senac.projetointegrador;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.*;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class MainActivity extends Activity {

    private TextView menuSeries, menuFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidUtils.setImmersiveMode(this,true);

        menuSeries = findViewById(R.id.menuSeries);
        menuFilmes = findViewById(R.id.menuFilmes);
    }

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void irHome(View view)
    {
        new edos.widget.Toast(this, R.layout.dialog_home,100).show();
    }

    public void irBusca(View view)
    {
        //Intent i = new Intent(this, SearchActivity.class);
        AndroidUtils.animate(this,R.anim.mover_direita,SearchActivity.class,500);
//        ActivityOptionsCompat a = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.fade_in, R.anim.mover_direita);
//        ActivityCompat.startActivity(MainActivity.this, i, a.toBundle());
//        startActivity(i);
       // finish();
    }

    public void irConfigs(View view)
    {
        Intent i = new Intent(this, ConfigActivity.class);
        startActivity(i);
        finish();
    }

    public void irFilmes(View view)
    {
        Intent i = new Intent(this, MenuFilmesActivity.class);
        startActivity(i);
        finish();
        menuSeries.setTextColor(getResources().getColor(R.color.corMenuOculto));
    }

    public void irSeries(View view)
    {
        Intent i = new Intent(this, MenuSeriesActivity.class);
        startActivity(i);
        finish();
        menuFilmes.setTextColor(getResources().getColor(R.color.corMenuOculto));
    }

}
