package br.com.senac.projetointegrador;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.*;
import android.widget.*;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class EditProfileActivity extends Activity {

    private EditText textoNomeAlterar, textoEmailAlterar, textoSenhaAlterar;
    private ImageButton imagemOlho;
    private Button botaoAlterar;
    private String campoNome, campoSenha, campoEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        AndroidUtils.setImmersiveMode(this,true);
        textoNomeAlterar = findViewById(R.id.textoNomeAlterar);
        textoEmailAlterar = findViewById(R.id.textoEmailAlterar);
        textoSenhaAlterar = findViewById(R.id.textoSenhaAlterar);
        botaoAlterar = findViewById(R.id.botaoAlterar);
        imagemOlho = findViewById(R.id.imagemOlho);

        imagemOlho.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    textoSenhaAlterar.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imagemOlho.setImageResource(R.drawable.olhoriscado);
                } else {
                    textoSenhaAlterar.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imagemOlho.setImageResource(R.drawable.olhonormal);
                }
                return false;
            }
        });
    }

    public void alterarDados()
    {

    }

    public void irProfile(View view)
    {
        Toast.makeText(getApplicationContext(),"Você já está no menu de conta.", Toast.LENGTH_SHORT).show();
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
