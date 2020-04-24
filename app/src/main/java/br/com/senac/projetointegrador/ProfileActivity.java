package br.com.senac.projetointegrador;

import android.content.Intent;
import android.app.*;
import android.os.*;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.*;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;

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

        //ANIMAÇÕES
        Slide trans1 = new Slide();
        trans1.setDuration(500);
        trans1.setSlideEdge(Gravity.START);
        Slide trans2 = new Slide();
        trans1.setDuration(500);
        trans1.setSlideEdge(Gravity.END);
        getWindow().setEnterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        getWindow().setReenterTransition(trans1);
        //ANIMAÇÕES

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
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
    }
}


