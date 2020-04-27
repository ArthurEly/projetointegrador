package br.com.senac.projetointegrador;
import android.app.*;
import android.os.*;
import android.text.*;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.*;
import android.view.*;
import br.com.senac.projetointegrador.util.*;
import org.json.*;
import android.content.*;
import java.io.*;

public class LoginActivity extends Activity {
	private int count;
	private EditText passw;
	private ImageButton imagemOlhoLogin;

	public LoginActivity() {
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
		final EditText email = findViewById(R.id.LOGIN_EMAIL);
		final EditText pass = findViewById(R.id.LOGIN_PASSWORD);
		Button login = findViewById(R.id.LOGIN_BUTTON);
		Button signup = findViewById(R.id.LOGIN_SIGNUP);
		imagemOlhoLogin = findViewById(R.id.imagemOlhoLogin);
		passw = findViewById(R.id.LOGIN_PASSWORD);

		
		login.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View p1) {
				Dialog d = new Dialog(self());
				d.setCancelable(false);
				d.setContentView(R.layout.dialog_loading);
				d.show();
				try {
					String emailInput = email.getText().toString();
					String passwordInput = pass.getText().toString();
					String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_USERS,"usuario_email",emailInput);
					String password = null;
					JSONObject json = null;
				
					json = NetworkUtils.parseDataBase(db, 0);
					password = json.getString("usuario_senha");
				
					if (passwordInput.equals(password)) {
						NetworkUtils.cacheUser(p1.getContext(), json);
						SharedPreferences.Editor e = AndroidUtils.getCache(p1.getContext()).edit();
						e.putBoolean("isFirstTime",false);
						e.commit();
						Intent i = new Intent(self(), MainActivity.class);
						startActivity(i);
						d.dismiss();
						finish();
					} else {
						android.widget.Toast.makeText(p1.getContext(),"Senha incorreta!",500).show();
					}
				} catch (JSONException e) {
					d.setContentView(R.layout.dialog_error);
					((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
				} catch (NullPointerException e) {
					d.dismiss();
					android.widget.Toast.makeText(p1.getContext(),"Preencha os campos!",500).show();
				}
			}
		});
		
		signup.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View view) {
				Intent i = new Intent(self(),RegisterActivity.class);
				startActivity(i);
				finish();
			}
		});
    }
	
	public Activity self() {
		return this;
	}

	public void verSenha(View view)
	{
		if (count % 2 == 0)
		{
			passw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			imagemOlhoLogin.setImageResource(R.drawable.olhoriscado);
		} else {
			passw.setTransformationMethod(PasswordTransformationMethod.getInstance());
			imagemOlhoLogin.setImageResource(R.drawable.olhonormal);
		}
		count++;
	}
}
