package br.com.senac.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class FilmeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);
        AndroidUtils.setImmersiveMode(this,true);

        //ANIMAÇÕES
        Slide trans1 = new Slide();
        trans1.setDuration(1500);
        Slide trans2 = new Slide();
        trans1.setDuration(800);
        getWindow().setEnterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        getWindow().setReenterTransition(trans1);
        //ANIMAÇÕES
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        this.startActivity(i, options.toBundle());
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
    }

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
    }
}
