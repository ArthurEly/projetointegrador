package br.com.senac.projetointegrador;

import android.net.wifi.aware.PublishConfig;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.widget.EditText;



public class ProfileActivity extends Activity {

    private TextView tituloNomeAlterar, tituloEmailAlterar, tituloSenhaAlterar;
    private EditText textoNomeAlterar, textoEmailAlterar, textoSenhaAlterar;
    private Button botaoMostrarMenuAlterar, botaoAlterar;
    private CheckBox checkBoxVisuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        botaoMostrarMenuAlterar = findViewById(R.id.botaoMostrarMenuAlterar);
        tituloNomeAlterar = findViewById(R.id.tituloNomeAlterar);
        tituloEmailAlterar = findViewById(R.id.tituloEmailAlterar);
        tituloSenhaAlterar = findViewById(R.id.tituloSenhaAlterar);
        textoNomeAlterar = findViewById(R.id.textoNomeAlterar);
        textoEmailAlterar = findViewById(R.id.textoEmailAlterar);
        textoSenhaAlterar = findViewById(R.id.textoSenhaAlterar);
        checkBoxVisuSenha = findViewById(R.id.checkBoxVisuSenha);
        botaoAlterar = findViewById(R.id.botaoAlterar);

    }

    public void mostrarMenu(View view)
    {
        tituloNomeAlterar.setVisibility(View.VISIBLE);
        tituloEmailAlterar.setVisibility(View.VISIBLE);
        tituloSenhaAlterar.setVisibility(View.VISIBLE);
        textoNomeAlterar.setVisibility(View.VISIBLE);
        textoSenhaAlterar.setVisibility(View.VISIBLE);
        textoEmailAlterar.setVisibility(View.VISIBLE);
        checkBoxVisuSenha.setVisibility(View.VISIBLE);
        botaoAlterar.setVisibility(View.VISIBLE);
    }

    public void verSenha (View view)
    {
        if (checkBoxVisuSenha.isChecked()) {
            textoSenhaAlterar.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else
        {
            textoSenhaAlterar.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}

