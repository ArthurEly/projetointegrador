package br.com.senac.projetointegrador;


import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.*;
import android.view.View;
import android.app.*;
import android.widget.LinearLayout;
import android.widget.TextView;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModel;
=======
//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityOptions;
>>>>>>> 1264ba03692f73860bca5acb2dea68e391c41e7e

import br.com.senac.projetointegrador.util.AndroidUtils;

public class MainActivity extends Activity {

    private TextView menuSeries, menuFilmes;
    private LinearLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidUtils.setImmersiveMode(this,true);

        //ANIMAÇÕES
        Explode trans1 = new Explode();
        trans1.setDuration(1200);
        Explode trans2 = new Explode();
        trans2.setDuration(800);
        getWindow().setEnterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        getWindow().setReenterTransition(trans1);
        //ANIMAÇÕES

        menuSeries = findViewById(R.id.menuSeries);
        menuFilmes = findViewById(R.id.menuFilmes);
        layoutMain = findViewById(R.id.layoutMain);
    }

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
    }


    public void irHome(View view)
    {
        new edos.widget.Toast(this, R.layout.dialog_home,100).show();

    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
    }

    public void irMenuFilmes(View view)
    {
        Intent i = new Intent(this, MenuFilmesActivity.class);
        ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        startActivity( i, activityOptions.toBundle());
        finish();
    }

    public void irMenuSeries(View view)
    {
        Intent i = new Intent(this, MenuSeriesActivity.class);
        ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        startActivity( i, activityOptions.toBundle());
        finish();
    }

    public void irFilme(View view)
    {
        Intent i = new Intent(this, FilmeActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity( i, options.toBundle());
        finish();
    }

    public void irSerie(View view)
    {
        Intent i = new Intent(this, SerieActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity( i, options.toBundle());
        finish();
    }

}
