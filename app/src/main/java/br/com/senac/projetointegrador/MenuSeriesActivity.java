package br.com.senac.projetointegrador;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityOptionsCompat;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class MenuSeriesActivity extends Activity {

    private TextView menuSeries, menuFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_series);
        AndroidUtils.setImmersiveMode(this,true);

        //ANIMAÇÕES
        Slide trans1 = new Slide();
        trans1.setDuration(800);
        trans1.setSlideEdge(Gravity.RIGHT);

        Slide trans2 = new Slide();
        trans2.setDuration(800);
        trans2.setSlideEdge(Gravity.RIGHT);

        getWindow().setEnterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        getWindow().setReenterTransition(trans1);
        //ANIMAÇÕES

        menuSeries = findViewById(R.id.menuSeries);
        menuFilmes = findViewById(R.id.menuFilmes);
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
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptions activityOptionsCompat = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        startActivity(i, activityOptionsCompat.toBundle());
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptions activityOptionsCompat = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity(i,activityOptionsCompat.toBundle());
        finish();
    }

    public void irMenuFilmes(View view)
    {
        Intent i = new Intent(this, MenuFilmesActivity.class);
        ActivityOptions activityOptionsCompat = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity(i, activityOptionsCompat.toBundle());
        if (isActivityTransitionRunning())
        {}
        else {
            finish(); }
    }

    public void irMenuSeries(View view)
    {
        new edos.widget.Toast(this,R.layout.dialog_home,100);
    }

    public void irSerie(View view)
    {
        Intent i = new Intent(this, SerieActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity(i, options.toBundle());
        finish();
    }
}
