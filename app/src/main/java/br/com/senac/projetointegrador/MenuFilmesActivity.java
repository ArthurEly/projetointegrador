package br.com.senac.projetointegrador;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class MenuFilmesActivity extends Activity {

    private TextView menuSeries, menuFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_filmes);
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
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
        finish();
    }


    public void irFilmes(View view)
    {
        new edos.widget.Toast(this,R.layout.dialog_home,100);
    }

    public void irSeries(View view)
    {
        Intent i = new Intent(this, MenuSeriesActivity.class);
        startActivity(i);
        finish();
    }
}
