package br.com.senac.projetointegrador;
import android.os.*;
import android.app.*;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.*;
import android.view.*;
import br.com.senac.projetointegrador.util.*;
import org.json.*;
import java.util.*;
import android.content.*;

public class RegisterActivity extends Activity {
	EditText nome,email,senha,confirmasenha,idade;
	CheckBox pro;
	Button b,l;
	ImageButton imagemOlho;
	private int count = 0;

	@Override public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_register);
		nome = findViewById(R.id.REGISTER_NAME);
		email = findViewById(R.id.REGISTER_EMAIL);
		senha = findViewById(R.id.REGISTER_PASSWORD);
		confirmasenha = findViewById(R.id.REGISTER_CONFIRM_PASSWORD);
		idade = findViewById(R.id.REGISTER_OLD);
		pro = findViewById(R.id.REGISTER_PRO);
		b = findViewById(R.id.REGISTER_BUTTON);
		l = findViewById(R.id.REGISTER_LOGIN);
		imagemOlho = findViewById(R.id.imagemOlhoRegister);


		l.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View p1) {
				Intent i = new Intent(self(), LoginActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		b.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View p1) {
				String nomeText = nome.getText().toString();
				String emailText = email.getText().toString();
				String senhaText = senha.getText().toString();
				String confirm = confirmasenha.getText().toString();
				int idadeText = Integer.parseInt(idade.getText().toString());
				boolean isPro = pro.isSelected();
				
				if (senhaText.equals(confirm)) {
					Dialog d = new Dialog(self());
					d.setCancelable(false);
					d.setContentView(R.layout.dialog_loading);
					d.show();
					try {
						ArrayList<JSONObject> duplicatas = new ArrayList<JSONObject>();
						String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_USERS,"usuario_email",emailText);
						JSONArray jsarray = new JSONArray(db);
						JSONObject json = null;
						
						// Pega todas as duplicatas
						for (int x = 0;x < jsarray.length();x++) {
							duplicatas.add(jsarray.getJSONObject(x));
						}
						
						if (duplicatas.toArray().length > 1)  {
							// há duplicatas do usuario
							d.setContentView(R.layout.dialog_error);
							((TextView) d.findViewById(R.id.error_dialog)).setText("Há duplicatas do seu usuário!\n\nContate o suporte para mais informações.");
						} else if (duplicatas.toArray().length == 0) {
							// o usuário não existe
							NetworkUtils.sqlPostUser(nomeText,emailText,senhaText,idadeText,isPro);
							db = NetworkUtils.sqlGet(NetworkUtils.TABLE_USERS,"usuario_email",emailText);
							json = NetworkUtils.parseDataBase(db,0);
							NetworkUtils.cacheUser(p1.getContext(), json);
							SharedPreferences.Editor e = AndroidUtils.getCache(p1.getContext()).edit();
							e.putBoolean("isFirstTime",false);
							e.commit();
							Intent i = new Intent(self(), MainActivity.class);
							startActivity(i);
							finish();
						} else if (duplicatas.toArray().length == 1) {
							// o usuário já existe
							Toast.makeText(p1.getContext(),"O usuário já existe!", 500).show();
						}

						
						NetworkUtils.cacheUser(p1.getContext(),json);
						d.dismiss();
					} catch(JSONException e) {
						d.setContentView(R.layout.dialog_error);
						((TextView) d.findViewById(R.id.error_dialog)).setText(ExceptionUtils.getErrorText(e));
					}
					
				} else {
					Toast.makeText(p1.getContext()," As senhas não são as mesmas.",500).show();
				}
			}
		});

//		imagemOlho.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//			}
//		});
	}
	
	public Activity self() {
		return this;
	}

	public void verSenha(View view)
	{
		Log.i("aroz", String.valueOf(count));
		if (count % 2 == 0) {
			senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			confirmasenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			imagemOlho.setImageResource(R.drawable.olhoriscado);
		}
		else {
			senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
			confirmasenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
			imagemOlho.setImageResource(R.drawable.olhonormal);
		}
		count++;
	}
}


