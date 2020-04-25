package br.com.senac.projetointegrador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Activity;

import android.widget.*;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import br.com.senac.projetointegrador.util.AndroidUtils;
import edos.widget.Toast;

public class AutenticacaoProfile extends Activity {

    private EditText campoSenha;
    private ImageButton imagemOlho;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autenticacaoprofile);
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

        campoSenha = findViewById(R.id.campoSenha);
        imagemOlho = findViewById(R.id.imagemOlhoAut);

        Intent i = getIntent();
        if (i != null)
        {
            Bundle params = i.getExtras();
            if (params != null){
                senha = params.getString("senha");
            }
        }

        imagemOlho.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    campoSenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imagemOlho.setImageResource(R.drawable.olhoriscado);
                } else {
                    campoSenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imagemOlho.setImageResource(R.drawable.olhonormal);
                }
                return false;
            }
        });
    }

    public void autenticacaoProfile(View view)
    {
        String campoSenhaLocal = campoSenha.getText().toString();

        if (campoSenhaLocal == null || campoSenhaLocal.equals(""))
        {
            Toast.makeText(this, "Digite sua senha", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (campoSenhaLocal.equals(senha))
            {
                Intent i = new Intent(this, EditProfileActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,null);
                ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
            }
            else
            {
                Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void voltar(View view){
        Intent i = new Intent(this, ProfileActivity.class);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.escurecer,R.anim.naofazertransicao);
        ActivityCompat.startActivity(this, i, activityOptionsCompat.toBundle());
        finish();
    }

}
