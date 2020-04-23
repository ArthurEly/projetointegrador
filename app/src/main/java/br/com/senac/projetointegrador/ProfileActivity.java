package br.com.senac.projetointegrador;

import android.content.Intent;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.TextView;

import br.com.senac.projetointegrador.util.AndroidUtils;
import edos.widget.Button;
import edos.widget.EditText;
import edos.widget.Toast;


public class ProfileActivity extends Activity {

    private EditText campoSenha;
    private TextView textoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AndroidUtils.setImmersiveMode(this,true);

        campoSenha = findViewById(R.id.campoSenha);
        textoSenha = findViewById(R.id.textoSenha);

    }

    public void irMenuEdit(View view) {
        edos.app.Dialog dialog = new edos.app.Dialog(this, R.layout.autenticacaoprofile);
        dialog.show();
    }

    public void autenticacaoProfile()
    {

    }

    public void irProfile(View view)
    {
        new edos.widget.Toast(this, R.layout.dialog_profile, 100).show();
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
        finish();
    }

    public void irConfigs(View view)
    {
        Intent i = new Intent(this, ConfigActivity.class);
        startActivity(i);
        finish();
    }
}


