package br.com.senac.projetointegrador;
import android.app.*;
import android.os.*;
import edos.widget.*;
import android.text.*;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
		EditText email = findViewById(R.id.LOGIN_EMAIL);
		EditText pass = findViewById(R.id.LOGIN_PASSWORD);
		
		email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}
