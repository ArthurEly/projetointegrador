package br.com.senac.projetointegrador;

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityOptions;

import android.app.*;
import android.content.*;
import android.os.*;
import android.transition.*;
import android.util.Log;
import android.view.*;
import android.widget.*;


import com.squareup.picasso.Picasso;

import br.com.senac.projetointegrador.util.*;
import org.json.*;
import br.com.senac.projetointegrador.view.*;
import java.util.*;

public class FilmeActivity extends Activity {
    TextView nome_serie, desc, autor;
    ArrayList<USB> usb = new ArrayList<USB>();
    AdaptadorUSB adaptadorUSB;
    int serieID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        serieID = extras.getInt("serie_id");
        System.out.println("id da serie é" + serieID);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);
        final ListView lista = findViewById(R.id.SERIE_LIST);
        setAnim();
        final edos.app.Dialog d = new edos.app.Dialog(this, R.layout.dialog_loading);
        d.show();
        LinearLayout l = findViewById(R.id.CAPA_FS);

        //ANIMAÇÕES
        Slide trans1 = new Slide();
        trans1.setDuration(750);
        trans1.setSlideEdge(Gravity.BOTTOM);
        Fade trans2 = new Fade();
        trans2.setDuration(500);
        getWindow().setEnterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        getWindow().setReenterTransition(trans1);
        //ANIMAÇÕES

        try {
            int serie = getIntent().getExtras().getInt("serie_id", 0);
            String filme = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_id", "" + serie);
            String episodios = NetworkUtils.sqlGet(NetworkUtils.TABLE_EPISODES, "serie_id","" + serie);
            // fiadaputa de "s"
            JSONObject filme_js = NetworkUtils.parseDataBase(filme, 0);
            JSONArray json = new JSONArray(filme);
            final JSONArray array_ep = new JSONArray(episodios);
            for (int x = 0; x < array_ep.length(); x++) {
                //cadê o (ovo) log
                final JSONObject j = array_ep.getJSONObject(x);
                USB u = new USB(j.getString("episodio_nome"),j.getString("episodio_descricao"),R.drawable.fundologin);

                usb.add(u);
            }

			adaptadorUSB = new AdaptadorUSB(this,R.layout.view_episodio,usb);

            nome_serie = findViewById(R.id.SERIE_TITLE);
            desc = findViewById(R.id.SERIE_DESCRIPT);
            autor = findViewById(R.id.SERIE_ARTHUR);

			lista.setAdapter(adaptadorUSB);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), "Bom filme!", Toast.LENGTH_LONG).show();
                        try {
                            Intent i = new Intent(self(), PlayerActivity.class);
                            i.putExtra("url", array_ep.getJSONObject(position).getString("episodio_url"));
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            d.setContentView(R.layout.dialog_error);
                            ((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
                        }
                    }
                });
                desc.setText(filme_js.getString("serie_descricao"));
                nome_serie.setText(filme_js.getString("serie_nome"));
                autor.setText(filme_js.getString("serie_autor"));
//abrir oq a

//                for (int x = 0; x < json.length(); x++) {
//
//                }

            d.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
            d.setContentView(R.layout.dialog_error);
            ((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
        }

        addImagem();
    }

    public Activity self() {
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        AndroidUtils.setImmersiveMode(this, true);
    }

    public void irHome(View view) {
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.escurecer, R.anim.naofazertransicao);
        this.startActivity(i, options.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 2000L);
    }

    public void irBusca(View view) {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.escurecer, R.anim.naofazertransicao);
        this.startActivity(i, options.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 2000L);
    }

    public void irProfile(View view) {
        Intent i = new Intent(this, ProfileActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.escurecer, R.anim.naofazertransicao);
        this.startActivity(i, options.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 2000L);
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

    public void addImagem()
    {
        try {
            //pega o layout de da imagem
            LinearLayout l = findViewById(R.id.CAPA_FS);

            /**
             * Pega a tabela inteira de lanças
             * @Note: A String n pode estar junta de JSONArray jsarray.
             */
            String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES);
            final JSONArray jsarray = new JSONArray(db);

                // pega a url da capa, armazenada em  serie_capa do Banco de dados;
                String url = jsarray.getJSONObject(serieID - 1).getString("serie_capa").toString();

                //se prepara para inflar a imagem
                LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie_infos,null,true);
                ImageView i = v.findViewById(R.id.view_serie_image_infos);

                // o Glide é um plugin que da cache na url e seta a imagem usando apenas um comando
                Picasso.get().load(url).into(i);

                // cria a imagem (v) dentro de l (o layout)
                i.setVisibility(View.VISIBLE);
                i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                l.addView(v);
                l.invalidate();

                // DEBUG das urls
                Log.i("MainActivity", url);

        } catch(Exception e) {
			//caso der erro, ele cria um dialogo de erro
            e.printStackTrace();
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        new Handler().postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 2000L);
    }
}
