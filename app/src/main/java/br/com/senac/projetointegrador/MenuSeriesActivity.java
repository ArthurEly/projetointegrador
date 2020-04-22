package br.com.senac.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class MenuSeriesActivity extends Activity {

    private TextView menuSeries, menuFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_series);
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
    }

    public void irSeries(View view)
    {
        new edos.widget.Toast(this,R.layout.dialog_home,100);
    }
}
