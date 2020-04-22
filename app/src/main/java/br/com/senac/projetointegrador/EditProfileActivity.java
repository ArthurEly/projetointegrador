package br.com.senac.projetointegrador;

import android.app.*;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.*;
import android.widget.*;

public class EditProfileActivity extends Activity {

    private EditText textoNomeAlterar, textoEmailAlterar, textoSenhaAlterar;
    private ImageButton imagemOlho;
    private Button botaoAlterar;
    private String campoNome, campoSenha, campoEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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


}
