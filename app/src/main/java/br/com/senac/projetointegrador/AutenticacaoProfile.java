package br.com.senac.projetointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import android.app.Activity;

import edos.widget.EditText;
import edos.widget.Toast;

public class AutenticacaoProfile extends Activity {

    private EditText campoSenha;
    private TextView textoSenha;
    private ImageButton imagemOlho;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autenticacaoprofile);

        campoSenha = findViewById(R.id.campoSenha);
        textoSenha = findViewById(R.id.textoSenha);
        imagemOlho = findViewById(R.id.imagemOlhoEdit);


        imagemOlho.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imagemOlho.setImageResource(R.drawable.olhoriscado);
                } else {
                    imagemOlho.setImageResource(R.drawable.olhonormal);
                }
                return false;
            }
        });
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
