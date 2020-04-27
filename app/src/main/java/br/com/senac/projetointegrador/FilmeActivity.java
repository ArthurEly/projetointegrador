package br.com.senac.projetointegrador;

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityOptions;

import android.app.*;
import android.content.*;
import android.os.*;
import android.transition.*;
import android.view.*;
import android.widget.*;
import br.com.senac.projetointegrador.util.*;
import org.json.*;
import br.com.senac.projetointegrador.view.*;
import java.util.*;

public class FilmeActivity extends Activity {
	TextView nome_serie,desc,autor;
	ListView lista;
	ArrayList<USB> usb;
	AdaptadorUSB adaptadorUSB;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);
		setAnim();
		edos.app.Dialog d = new edos.app.Dialog(this, R.layout.dialog_loading);
		d.show();
		try {
			int serie = getIntent().getExtras().getInt("serie_id",0);
			String filme = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_id","" + serie);
			String episodios = NetworkUtils.sqlGet(NetworkUtils.TABLE_EPISODES, "series_id","" + serie);
			adaptadorUSB = new AdaptadorUSB(this,R.layout.view_episodio,usb);
			JSONObject filme_js = NetworkUtils.parseDataBase(filme,0);
			JSONArray json = new JSONArray(episodios);
	
			lista = findViewById(R.id.SERIE_LIST);
			nome_serie = findViewById(R.id.SERIE_TITLE);
			desc = findViewById(R.id.SERIE_DESCRIPT);
			autor = findViewById(R.id.SERIE_ARTHUR);
			
			lista.setAdapter(adaptadorUSB);
			desc.setText(filme_js.getString("serie_descricao"));
			nome_serie.setText(filme_js.getString("serie_nome"));
			autor.setText(filme_js.getString("serie_autor"));
			
			for (int x = 0;x < json.length();x++) {
				
			}
			
			d.dismiss();
		} catch(JSONException e) {
			d.setContentView(R.layout.dialog_error);
			((TextView)d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
    }
	
	@Override public void onResume() {
		super.onResume();
        AndroidUtils.setImmersiveMode(this,true);
	}

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        this.startActivity(i, options.toBundle());
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptions options =ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        this.startActivity(i, options.toBundle());
        finish();
    }

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
        ActivityOptions options =ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        this.startActivity(i, options.toBundle());
        finish();
    }
	
	public void setAnim() {
        Slide trans1 = new Slide();
        trans1.setDuration(1500);
        Slide trans2 = new Slide();
        trans1.setDuration(800);
        getWindow().setEnterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        getWindow().setReenterTransition(trans1);
	}
}
