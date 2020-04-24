package br.com.senac.projetointegrador;
import android.app.*;
import android.os.*;
import android.text.*;
import android.widget.*;
import android.view.*;
import br.com.senac.projetointegrador.util.*;
import org.json.*;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
		final EditText email = findViewById(R.id.LOGIN_EMAIL);
		final EditText pass = findViewById(R.id.LOGIN_PASSWORD);
		Button login = findViewById(R.id.LOGIN_BUTTON);
		
		login.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View p1) {
				String emailInput = email.getText().toString();
				String passwordInput = pass.getText().toString();
				String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_USERS,"usuario_email",emailInput);
				String password = null;
				JSONObject json = null;
				
				try {
					json =  NetworkUtils.parseDataBase(db, 0);
					password = json.getString("usuario_senha");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				if (passwordInput.equals(password)) {
					android.widget.Toast.makeText(p1.getContext(),"sucesso",500).show();
					try
					{
						NetworkUtils.cacheUser(p1.getContext(), json);
					}
					catch (JSONException e)
					{e.printStackTrace();}
				}
 else
 {
					android.widget.Toast.makeText(p1.getContext(),"senha incorreta",500).show();
				}
				
			}
		});
    }
}
