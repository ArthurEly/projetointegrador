package br.com.senac.projetointegrador;


import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.*;
import android.view.View;
import android.app.*;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import br.com.senac.projetointegrador.util.AndroidUtils;
import android.util.*;
import br.com.senac.projetointegrador.util.*;
import org.json.*;

public class MainActivity extends Activity {

    private TextView menuSeries, menuFilmes;
    private LinearLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		// checa alterações no banco de dados (não deletar)
		checkLogin();

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
	
	@Override protected void onResume() {
		super.onResume();
        AndroidUtils.setImmersiveMode(this,true);
	}

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
<<<<<<< HEAD
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
=======
        ActivityOptions options =ActivityOptions.makeCustomAnimation(this, R.anim.escurecer,R.anim.naofazertransicao);
>>>>>>> cb2c790468fb9f276525e2cbd81d09aa8ac0642d
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
<<<<<<< HEAD
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
=======
        ActivityOptions options =ActivityOptions.makeCustomAnimation(this, R.anim.escurecer,R.anim.naofazertransicao);
>>>>>>> cb2c790468fb9f276525e2cbd81d09aa8ac0642d
        this.startActivity(i, options.toBundle());
        finish();
    }

    public void irMenuFilmes(View view)
    {
        Intent i = new Intent(this, MenuFilmesActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }

    public void irMenuSeries(View view)
    {
        Intent i = new Intent(this, MenuSeriesActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }

    public void irFilme(View view) {
        Intent i = new Intent(this, FilmeActivity.class);
<<<<<<< HEAD
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        ActivityCompat.startActivity(this, i, options.toBundle());
=======
		
		// @TODO: Trocar "0" pelo id da serie
		i.putExtra("serie_id",0);
        ActivityOptions options =ActivityOptions.makeCustomAnimation(this, R.anim.escurecer,R.anim.naofazertransicao);
        startActivity( i, options.toBundle());
>>>>>>> cb2c790468fb9f276525e2cbd81d09aa8ac0642d
        finish();
    }

    public void irSerie(View view)
    {
        Intent i = new Intent(this, SerieActivity.class);
<<<<<<< HEAD
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        ActivityCompat.startActivity(this, i, options.toBundle());
=======
        ActivityOptions options =ActivityOptions.makeCustomAnimation(this, R.anim.escurecer,R.anim.naofazertransicao);
        startActivity( i, options.toBundle());
>>>>>>> cb2c790468fb9f276525e2cbd81d09aa8ac0642d
        finish();
    }

	public Activity self() {
		return this;
	}
	
	public void addSeries() {
		
	}
	
	public void checkLogin() {
		try {
			NetworkUtils.cacheUser(this, AndroidUtils.getCache(this).getInt("usuario_id", 0));
		} catch (JSONException e) {
			e.printStackTrace();
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			((TextView) d.findViewById(R.id.error_dialog)).setText("Ocorreu um erro de conexão, por favor faça o login novamente!");
			AndroidUtils.getCache(this).edit().putBoolean("isFirstTime",true).commit();
			d.getOkButton().setOnClickListener(new View.OnClickListener() {
				@Override public void onClick(View p1) {
					Intent i = new Intent(self(), LoginActivity.class);
					startActivity(i);
					finish();
				}
			});
		}
	}
}
