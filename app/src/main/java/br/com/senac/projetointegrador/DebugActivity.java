package br.com.senac.projetointegrador;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;

public class DebugActivity extends Activity {
	TextView splash,debug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
		
		splash = findViewById(R.id.SPLASH_ACTIVITY);
		debug = findViewById(R.id.MAIN_ACTIVITY);
		
		splash.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View p1) {
				initActivity(SplashActivity.class);
			}
		});
		
		debug.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View p1) {
				initActivity(MainActivity.class);
			}
		});
    }
	
	public void initActivity(Class cls) {
		Intent i = new Intent(this, cls);
		startActivity(i);
	}
}
