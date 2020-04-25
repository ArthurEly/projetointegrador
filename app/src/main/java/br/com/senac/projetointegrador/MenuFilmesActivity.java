package br.com.senac.projetointegrador;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class MenuFilmesActivity extends Activity {

    private TextView menuSeries, menuFilmes;
    private ImageView filme11;
    int idfilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_filmes);
        AndroidUtils.setImmersiveMode(this,true);

        //ANIMAÇÕES
        Slide trans1 = new Slide();
        trans1.setDuration(800);
        trans1.setSlideEdge(Gravity.LEFT);
        Slide trans2 = new Slide();
        trans2.setDuration(800);
        trans2.setSlideEdge(Gravity.LEFT);
        getWindow().setEnterTransition(trans1);
        getWindow().setReenterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        //ANIMAÇÕES

        //            TRANSIÇÃO ANIMADA COM OS ELEMENTOS MUDANDO DE BAGULHO;
            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transitions);
            transition.setDuration(2000);
            getWindow().setSharedElementEnterTransition(transition);
            getWindow().setSharedElementExitTransition(transition);

        menuSeries = findViewById(R.id.menuSeries);
        menuFilmes = findViewById(R.id.menuFilmes);
        filme11 = findViewById(R.id.filme11);
        idfilme = filme11.getId();
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
//        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
//        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        startActivity(i);
        finish();
        overridePendingTransition(0, android.R.anim.fade_out);
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }


    public void irMenuFilmes(View view)
    {
        new edos.widget.Toast(this,R.layout.dialog_home,100);
    }

    public void irMenuSeries(View view)
    {
        Intent i = new Intent(this, MenuSeriesActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }


    public void irFilme(View view)
    {
        Intent i = new Intent(this, FilmeActivity.class);
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        ActivityCompat.startActivity(this, i, options.toBundle());
        finish();
    }

}
