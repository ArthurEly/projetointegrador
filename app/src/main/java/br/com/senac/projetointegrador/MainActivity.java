package br.com.senac.projetointegrador;


import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.*;
import android.view.View;
import android.app.*;
import android.widget.LinearLayout;
import android.widget.TextView;

//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityOptions;

import br.com.senac.projetointegrador.util.AndroidUtils;
import android.util.*;
import br.com.senac.projetointegrador.util.*;
import org.json.*;
import java.util.*;
import android.widget.*;
import android.view.*;
import com.bumptech.glide.*;
import android.graphics.*;

public class MainActivity extends Activity {

    private TextView menuSeries, menuFilmes;
    private LinearLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		// checa alterações no banco de dados (não deletar)
		checkLogin();
		
		//adiciona as series ao main
		addLanças();

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
        ActivityOptions options =ActivityOptions.makeCustomAnimation(this, R.anim.escurecer,R.anim.naofazertransicao);
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
        ActivityOptions options =ActivityOptions.makeCustomAnimation(this, R.anim.escurecer,R.anim.naofazertransicao);
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

    public void irFilme(View view) {
		
		// @TODO: Remover essa função por completo
		
    }

    public void irSerie(View view)
    {
        Intent i = new Intent(this, SerieActivity.class);
        ActivityOptions options =ActivityOptions.makeCustomAnimation(this, R.anim.escurecer,R.anim.naofazertransicao);
        startActivity( i, options.toBundle());
        finish();
    }

	public Activity self() {
		return this;
	}
	
	public void addLanças() {
		try {
			LinearLayout l = findViewById(R.id.MAIN_LANÇAMENTOS);
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES);
			final JSONArray jsarray = new JSONArray(db);
			JSONObject json = null;

			for (int x = 0;x < jsarray.length();x++) {
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();
				final int y = x;
				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image);
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions options =ActivityOptions.makeCustomAnimation(self(), R.anim.escurecer,R.anim.naofazertransicao);
							startActivity(t, options.toBundle());
							finish();
						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
					});
				Glide.with(this).load(url).into(i);
				i.setVisibility(View.VISIBLE);
				l.addView(v);
				//i.setLayoutParams(new ViewGroup.LayoutParams(110,150));
				l.invalidate();
				Log.i("MainActivity", url);
			}
			
		} catch(Exception e) {
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}
	
	public void checkLogin() {
		try {
			NetworkUtils.cacheUser(this, AndroidUtils.getCache(this).getInt("usuario_id", 0));
		} catch (JSONException e) {
			e.printStackTrace();
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
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
