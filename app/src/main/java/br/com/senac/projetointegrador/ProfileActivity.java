package br.com.senac.projetointegrador;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.widget.EditText;



public class ProfileActivity extends Activity {

    private TextView tituloNomeAlterar, tituloEmailAlterar, tituloSenhaAlterar;
    private EditText textoNomeAlterar, textoEmailAlterar, textoSenhaAlterar;
    private Button botaoMostrarMenuAlterar, botaoAlterar;
    private ImageButton imagemOlho;
   // private CheckBox checkBoxVisuSenha;
    private String campoNome, campoSenha, campoEmail;
    private LinearLayout linearNome, linearEmail, linearSenha;

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
      //checkBoxVisuSenha = findViewById(R.id.checkBoxVisuSenha);
        botaoAlterar = findViewById(R.id.botaoAlterar);
        imagemOlho = findViewById(R.id.imagemOlho);
        linearNome = findViewById(R.id.linearNome);
        linearEmail = findViewById(R.id.linearEmail);
        linearSenha = findViewById(R.id.linearSenha);

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

    public void mostrarMenu(View view)
    {
//        tituloNomeAlterar.setVisibility(View.VISIBLE);
//        tituloEmailAlterar.setVisibility(View.VISIBLE);
//        tituloSenhaAlterar.setVisibility(View.VISIBLE);
//        textoNomeAlterar.setVisibility(View.VISIBLE);
//        textoSenhaAlterar.setVisibility(View.VISIBLE);
//        textoEmailAlterar.setVisibility(View.VISIBLE);
//        imagemOlho.setVisibility(View.VISIBLE);
        linearNome.setVisibility(View.VISIBLE);
        linearEmail.setVisibility(View.VISIBLE);
        linearSenha.setVisibility(View.VISIBLE);
        botaoAlterar.setVisibility(View.VISIBLE);
    }
}

//     public void verSenha (View view)
//    {
//        if (imagemOlho.isInTouchMode())
//        {
//            textoSenhaAlterar.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//            imagemOlho.setImageResource(R.drawable.olhoriscado);
//        }
//
//        if (!imagemOlho.isInTouchMode())
//        {
//            textoSenhaAlterar.setTransformationMethod(PasswordTransformationMethod.getInstance());
//            imagemOlho.setImageResource(R.drawable.olhonormal);
//        }
//    }
//}

