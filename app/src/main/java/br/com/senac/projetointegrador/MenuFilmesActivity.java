package br.com.senac.projetointegrador;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

//import androidx.core.app.this;
//import androidx.core.app.ActivityOptions;
//import androidx.core.util.Pair;

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
    }

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
        ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity( i, op.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run () {
                finish();
            }
        }, 2000L);
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptions activityOptionsCompat = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        startActivity(i, activityOptionsCompat.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run () {
                finish();
            }
        }, 2000L);
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity( i, op.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run () {
                finish();
            }
        }, 2000L);
    }


    public void irMenuFilmes(View view)
    {
        new edos.widget.Toast(this,R.layout.dialog_home,100);
    }

    public void irMenuSeries(View view)
    {
        Intent i = new Intent(this, MenuSeriesActivity.class);
        ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity( i, op.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run () {
                finish();
            }
        }, 2000L);
    }

    public void irFilme(View view)
    {
        Intent i = new Intent(this, FilmeActivity.class);
        ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity( i, op.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run () {
                finish();
            }
        }, 2000L);
    }
}
