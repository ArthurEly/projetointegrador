package br.com.senac.projetointegrador;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import br.com.senac.projetointegrador.util.*;
import android.util.*;

public class DebugActivity extends Activity {
	TextView splash,main,profile,login,player,edit,json;
	
	public void debugFunction() {
		
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
		
		splash = findViewById(R.id.SPLASH_ACTIVITY);
		main = findViewById(R.id.MAIN_ACTIVITY);
		profile = findViewById(R.id.PROFILE_ACTIVITY);
		login = findViewById(R.id.LOGIN_ACTIVITY);
		player = findViewById(R.id.PLAYER_ACTIVITY);
		edit = findViewById(R.id.EDITPROFILE_ACTIVITY);
		json = findViewById(R.id.JSON_ACTIVITY);
		
		splash.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View p1) {
				initActivity(SplashActivity.class);
			}
		});
		
		main.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View p1) {
				initActivity(MainActivity.class);
			}
		});
		
		profile.setOnClickListener(new OnClickListener() {
				@Override public void onClick(View p1) {
					initActivity(ProfileActivity.class);
				}
			});

		login.setOnClickListener(new OnClickListener() {
				@Override public void onClick(View p1) {
					initActivity(LoginActivity.class);
				}
			});
		
		player.setOnClickListener(new OnClickListener() {
				@Override public void onClick(View p1) {
					initActivity(PlayerActivity.class);
				}
			});

		edit.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View p1) {
				initActivity(EditProfileActivity.class);
			}
		});

		json.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View p1) {
				initActivity(JSONTeste.class);
			}
		});
			
		debugFunction();
    }
	
	public void initActivity(Class cls) {
		Intent i = new Intent(this, cls);
		startActivity(i);
	}
}
