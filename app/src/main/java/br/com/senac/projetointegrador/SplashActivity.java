package br.com.senac.projetointegrador;

import android.app.*;
import android.os.*;
import br.com.senac.projetointegrador.util.*;
import android.content.*;

public class SplashActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
		
		final SharedPreferences cache = getSharedPreferences("cache",MODE_PRIVATE);
		
		new Handler().postDelayed(new Runnable() {
			public void run () {
				if (cache.getBoolean("isFirstTime",true)) {
					initActivity(LoginActivity.class);
				} else {
					initActivity(MainActivity.class);
				}
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
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
}
