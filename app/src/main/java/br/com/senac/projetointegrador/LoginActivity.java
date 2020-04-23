package br.com.senac.projetointegrador;
import android.app.*;
import android.os.*;
import android.text.*;
import android.widget.*;
import android.view.*;
import br.com.senac.projetointegrador.util.*;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
		final EditText email = findViewById(R.id.LOGIN_EMAIL);
		EditText pass = findViewById(R.id.LOGIN_PASSWORD);
		Button login = findViewById(R.id.LOGIN_BUTTON);
		
		login.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View p1) {
				String emailInput = email.getText().toString();
				String db = NetworkUtils.sqlGet(NetworkUtils.TABLE_USERS,"usuario_email",emailInput);
				android.widget.Toast.makeText(p1.getContext(),db,500).show();
			}
		});
    }
}
