package br.com.senac.projetointegrador;

import android.app.*;
import android.os.*;
import br.com.senac.projetointegrador.util.*;

public class SplashActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus = true) {
            AndroidUtils.setImmersiveMode(this,true);
        }
    }
}
