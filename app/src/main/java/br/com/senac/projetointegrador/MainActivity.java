package br.com.senac.projetointegrador;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.transition.Fade;
import android.transition.*;
import android.view.View;
import android.app.*;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModel;
//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityOptions;
//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityOptions;

import br.com.senac.projetointegrador.util.AndroidUtils;
import android.util.*;
import br.com.senac.projetointegrador.util.*;
import org.json.*;
import java.util.*;
import android.widget.*;
import android.view.*;
import android.graphics.*;
import com.squareup.picasso.*;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {

    private LinearLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		// checa alterações no banco de dados (não deletar)
		checkLogin();

		//adiciona a sugestao
		addSugestao();

		//adiciona as series ao main
		addLanças();

		//adiciona os filmes de terror q no caso tem 1 só kkkkk
		addTerror();

		//ja deve saber oq faz ne
		addAventura();
		addAção();
		addComedia();
		addDrama();
		addRomance();
		addSuspense();

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

        layoutMain = findViewById(R.id.layoutMain);
    }
	
	@Override protected void onResume() {
		super.onResume();
        AndroidUtils.setImmersiveMode(this,true);
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
        new edos.widget.Toast(this, R.layout.dialog_home,100).show();

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

    public void irFilme(View view) {
		
		// @TODO: Remover essa função por completo
		
    }

    public void irSerie(View view)
    {
        Intent i = new Intent(this, SerieActivity.class);
		ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity( i, op.toBundle());
		new Handler().postDelayed(new Runnable() {
			public void run () {
				finish();
			}
		}, 2000L);
    }

	public Activity self() {
		return this;
	}

	public void addLanças() {
		try {
			//pega o layout de lanças
			LinearLayout l = findViewById(R.id.MAIN_LANÇAMENTOS);

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES);
			final JSONArray jsarray = new JSONArray(db);

			for (int x = 0;x < jsarray.length();x++) {
				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamálo no onClick
				final int y = x;

				//se prepara para inflar a imagem
				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image);

				// cria uma ação de clique para a imagem
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// o Glide é um plugin que da cache na url e seta a imagem usando apenas um comando
				Picasso.get().load(url).into(i);

				// cria a imagem (v) dentro de l (o layout)
				i.setVisibility(View.VISIBLE);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				l.addView(v);
				l.invalidate();

				// DEBUG das urls
				Log.i("MainActivity", url);
			}

		} catch(Exception e) {
//			//caso der erro, ele cria um dialogo de erro
//			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
//			d.show();
//			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}

	public void addSugestao() {
		try {
			//pega o layout de lanças
			LinearLayout l = findViewById(R.id.MAIN_SUGESTAO);
			final int rand = new Random().ints(0,9).findAny().getAsInt();

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES);
			final JSONArray jsarray = new JSONArray(db);

				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(rand).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamálo no onClick

				//se prepara para inflar a imagem
				ImageView i_f = findViewById(R.id.infoSugestao);
				ImageView i_p = findViewById(R.id.playerSugestao);

				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie_sugestao,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image_sugestao);

				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(rand).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// cria uma ação de clique para a imagem
				i_f.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(rand).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				i_p.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						try {
							Intent i = new Intent(self(), PlayerActivity.class);
							i.putExtra("url", jsarray.getJSONObject(rand).getString("episodio_url"));
							startActivity(i);
						} catch (JSONException e) {

						}
					}
				});

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
//			//caso der erro, ele cria um dialogo de erro
//			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
//			d.show();
//			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}

	public void addTerror() {
		try {
			//pega o layout de terror
			LinearLayout l = findViewById(R.id.MAIN_TERROR);

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_categoria", "Terror");
			final JSONArray jsarray = new JSONArray(db);

			for (int x = 0;x < jsarray.length();x++) {
				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamá-lo no onClick
				final int y = x;

				//se prepara para inflar a imagem
				ImageView i = findViewById(R.id.view_serie_image);

				// cria uma ação de clique para a imagem
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// o Picasso é um plugin que da cache na url e seta a imagem usando apenas um comando
				Picasso.get().load(url).into(i);

				// cria a imagem (v) dentro de l (o layout)
				i.setVisibility(View.VISIBLE);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				l.invalidate();

				// DEBUG das urls
				Log.i("MainActivity", url);
			}

		} catch(Exception e) {
			//caso der erro, ele cria um dialogo de erro
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}

	public void addRomance() {
		try {
			//pega o layout de terror
			LinearLayout l = findViewById(R.id.MAIN_ROMANCE);

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_categoria", "Romance");
			final JSONArray jsarray = new JSONArray(db);

			for (int x = 0;x < jsarray.length();x++) {
				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamá-lo no onClick
				final int y = x;

				//se prepara para inflar a imagem
				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image);

				// cria uma ação de clique para a imagem
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// o Picasso é um plugin que da cache na url e seta a imagem usando apenas um comando
				Picasso.get().load(url).into(i);

				// cria a imagem (v) dentro de l (o layout)
				i.setVisibility(View.VISIBLE);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				l.addView(v);
				l.invalidate();

				// DEBUG das urls
				Log.i("MainActivity", url);
			}

		} catch(Exception e) {
			//caso der erro, ele cria um dialogo de erro
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}

	public void addAventura() {
		try {
			//pega o layout de terror
			LinearLayout l = findViewById(R.id.MAIN_AVENTURA);

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_categoria", "Aventura");
			final JSONArray jsarray = new JSONArray(db);

			for (int x = 0;x < jsarray.length();x++) {
				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamá-lo no onClick
				final int y = x;

				//se prepara para inflar a imagem
				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image);

				// cria uma ação de clique para a imagem
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// o Picasso é um plugin que da cache na url e seta a imagem usando apenas um comando
				Picasso.get().load(url).into(i);

				// cria a imagem (v) dentro de l (o layout)
				i.setVisibility(View.VISIBLE);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				l.addView(v);
				l.invalidate();

				// DEBUG das urls
				Log.i("MainActivity", url);
			}

		} catch(Exception e) {
			//caso der erro, ele cria um dialogo de erro
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}

	public void addDrama() {
		try {
			//pega o layout de terror
			LinearLayout l = findViewById(R.id.MAIN_DRAMA);

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_categoria", "Drama");
			final JSONArray jsarray = new JSONArray(db);

			for (int x = 0;x < jsarray.length();x++) {
				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamá-lo no onClick
				final int y = x;

				//se prepara para inflar a imagem
				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image);

				// cria uma ação de clique para a imagem
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// o Picasso é um plugin que da cache na url e seta a imagem usando apenas um comando
				Picasso.get().load(url).into(i);

				// cria a imagem (v) dentro de l (o layout)
				i.setVisibility(View.VISIBLE);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				l.addView(v);
				l.invalidate();

				// DEBUG das urls
				Log.i("MainActivity", url);
			}

		} catch(Exception e) {
			//caso der erro, ele cria um dialogo de erro
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}

	public void addAção() {
		try {
			//pega o layout de terror
			LinearLayout l = findViewById(R.id.MAIN_ACAO);

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_categoria", "Ação");
			final JSONArray jsarray = new JSONArray(db);

			for (int x = 0;x < jsarray.length();x++) {
				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamá-lo no onClick
				final int y = x;

				//se prepara para inflar a imagem
				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image);

				// cria uma ação de clique para a imagem
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// o Picasso é um plugin que da cache na url e seta a imagem usando apenas um comando
				Picasso.get().load(url).into(i);

				// cria a imagem (v) dentro de l (o layout)
				i.setVisibility(View.VISIBLE);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				l.addView(v);
				l.invalidate();

				// DEBUG das urls
				Log.i("MainActivity", url);
			}

		} catch(Exception e) {
			//caso der erro, ele cria um dialogo de erro
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}

	public void addSuspense() {
		try {
			//pega o layout de terror
			LinearLayout l = findViewById(R.id.MAIN_SUSPENSE);

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_categoria", "Suspense");
			final JSONArray jsarray = new JSONArray(db);

			for (int x = 0;x < jsarray.length();x++) {
				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamá-lo no onClick
				final int y = x;

				//se prepara para inflar a imagem
				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image);

				// cria uma ação de clique para a imagem
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// o Picasso é um plugin que da cache na url e seta a imagem usando apenas um comando
				Picasso.get().load(url).into(i);

				// cria a imagem (v) dentro de l (o layout)
				i.setVisibility(View.VISIBLE);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				l.addView(v);
				l.invalidate();

				// DEBUG das urls
				Log.i("MainActivity", url);
			}

		} catch(Exception e) {
			//caso der erro, ele cria um dialogo de erro
			edos.app.Dialog d = new edos.app.Dialog(this,R.layout.dialog_error);
			d.show();
			((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
		}
	}

	public void addComedia() {
		try {
			//pega o layout de terror
			LinearLayout l = findViewById(R.id.MAIN_COMEDIA);

			/**
			 * Pega a tabela inteira de lanças
			 * @Note: A String n pode estar junta de JSONArray jsarray.
			 */
			String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_SERIES, "serie_categoria", "Comédia");
			final JSONArray jsarray = new JSONArray(db);

			for (int x = 0;x < jsarray.length();x++) {
				// pega a url da capa, armazenada em  serie_capa do Banco de dados;
				String url = jsarray.getJSONObject(x).getString("serie_capa").toString();

				// transforma x em final, pois n é possivel chamá-lo no onClick
				final int y = x;

				//se prepara para inflar a imagem
				LinearLayout v = (LinearLayout) getLayoutInflater().inflate(R.layout.view_serie,null,true);
				ImageView i = v.findViewById(R.id.view_serie_image);

				// cria uma ação de clique para a imagem
				i.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						try {
							Intent t = new Intent(self(),FilmeActivity.class);
							t.putExtra("serie_id",jsarray.getJSONObject(y).getInt("serie_id"));
							ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(self(), null);
							startActivity(t, op.toBundle());
							new Handler().postDelayed(new Runnable() {
								public void run () {
									finish();
								}
							}, 2000L);

						} catch(Exception e) {
							edos.app.Dialog d = new edos.app.Dialog(self(),R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
						}
					}
				});

				// o Picasso é um plugin que da cache na url e seta a imagem usando apenas um comando
				Picasso.get().load(url).into(i);

				// cria a imagem (v) dentro de l (o layout)
				i.setVisibility(View.VISIBLE);
				i.setScaleType(ImageView.ScaleType.CENTER_CROP);
				l.addView(v);
				l.invalidate();

				// DEBUG das urls
				Log.i("MainActivity", url);
			}

		} catch(Exception e) {
			//caso der erro, ele cria um dialogo de erro
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
					new Handler().postDelayed(new Runnable() {
						public void run () {
							finish();
						}
					}, 2000L);
				}
			});
		}
	}

}
