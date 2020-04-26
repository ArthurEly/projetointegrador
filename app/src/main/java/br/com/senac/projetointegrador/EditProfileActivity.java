package br.com.senac.projetointegrador;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.transition.Slide;
import android.view.*;
import android.widget.*;

//import androidx.core.app.ActivityOptions;

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

        //ANIMAÇÕES
        Slide trans1 = new Slide();
        trans1.setDuration(500);
        trans1.setSlideEdge(Gravity.RIGHT);
        Slide trans2 = new Slide();
        trans2.setDuration(500);
        trans2.setSlideEdge(Gravity.LEFT);
        getWindow().setEnterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        getWindow().setReenterTransition(trans1);
        //ANIMAÇÕES

        textoNomeAlterar = findViewById(R.id.textoNomeAlterar);
        textoEmailAlterar = findViewById(R.id.textoEmailAlterar);
        textoSenhaAlterar = findViewById(R.id.textoSenhaAlterar);
        botaoAlterar = findViewById(R.id.botaoAlterar);
        imagemOlho = findViewById(R.id.imagemOlhoAut);

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
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
    }
}
