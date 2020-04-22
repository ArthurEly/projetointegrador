package br.com.senac.projetointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edos.widget.EditText;
import edos.widget.Toast;

public class AutenticacaoProfile extends AppCompatActivity {

    private EditText campoSenha;
    private TextView textoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autenticacaoprofile);

        campoSenha = findViewById(R.id.campoSenha);
        textoSenha = findViewById(R.id.textoSenha);
    }

    public void autenticacaoProfile()
    {
        String campoSenhaLocal = campoSenha.getText().toString();
        String textoSenhaLocal = textoSenha.getText().toString();

        if (campoSenhaLocal == null || campoSenhaLocal == "")
        {
            Toast.makeText(this, "Digite sua senha", Toast.LENGTH_SHORT);
        }
        else
        {
            if (campoSenhaLocal == textoSenhaLocal )
            {
                Intent i = new Intent(this, EditProfileActivity.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(this, "Senha incorreta.", Toast.LENGTH_SHORT);
            }
        }
    }
}
