package br.com.senac.projetointegrador;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class MenuFilmesActivity extends Activity {

    private TextView menuSeries, menuFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_filmes);
        AndroidUtils.setImmersiveMode(this,true);

        //ANIMAÇÕES
        Slide trans1 = new Slide();
        trans1.setDuration(800);
        trans1.setSlideEdge(Gravity.START);

        Slide trans2 = new Slide();
        trans2.setDuration(400);
        trans2.setSlideEdge(Gravity.END);

        getWindow().setEnterTransition(trans1);
        getWindow().setReenterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);

        //ANIMAÇÕES

        menuSeries = findViewById(R.id.menuSeries);
        menuFilmes = findViewById(R.id.menuFilmes);
    }

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }


    public void irFilmes(View view)
    {
        new edos.widget.Toast(this,R.layout.dialog_home,100);
    }

    public void irSeries(View view)
    {
        Intent i = new Intent(this, MenuSeriesActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }
}
