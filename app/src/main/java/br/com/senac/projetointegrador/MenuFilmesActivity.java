package br.com.senac.projetointegrador;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityOptionsCompat;
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
        trans1.setSlideEdge(Gravity.START);
        Slide trans2 = new Slide();
        trans2.setDuration(400);
        trans2.setSlideEdge(Gravity.END);
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
        ActivityOptions activityOptionsCompat = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity(i, activityOptionsCompat.toBundle());
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
        new edos.widget.Toast(this,R.layout.dialog_home,100);
    }

    public void irMenuSeries(View view)
    {
        Intent i = new Intent(this, MenuSeriesActivity.class);
        ActivityOptions activityOptionsCompat = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity(i, activityOptionsCompat.toBundle());
        finish();
    }

    public void irFilme(View view)
    {
        Intent i = new Intent(this, FilmeActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity(i, options.toBundle());
        finish();
    }
}
