package br.com.senac.projetointegrador;

import android.app.*;
import android.media.MediaPlayer;
import android.os.*;
import br.com.senac.projetointegrador.util.*;
import android.content.*;
import android.transition.Explode;
import android.view.View;

public class SplashActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        playAudio();

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

		final SharedPreferences cache = getSharedPreferences("cache",MODE_PRIVATE);
		
		new Handler().postDelayed(new Runnable() {
			public void run () {
				if (cache.getBoolean("isFirstTime",true)) {
					initActivity(LoginActivity.class);
				} else {
					Intent i = new Intent(SplashActivity.this,MainActivity.class);
					ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, null);
					startActivity(i, op.toBundle());

				}
				finish();
			}
		}, 1500L);
    }

    @Override public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus = true) {
            AndroidUtils.setImmersiveMode(this,true);
        }
    }
	
	public void initActivity(Class cls) {
		Intent i = new Intent(this, cls);
		startActivity(i);
	}

	public void playAudio(){
		MediaPlayer mp = MediaPlayer.create(this, R.raw.netflixintro);
		mp.setVolume(30,30);
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
			public void onCompletion(MediaPlayer mp) {
				mp.stop();
				mp.release();
				mp = null;
			}
		});
		mp.start();
	}
}
