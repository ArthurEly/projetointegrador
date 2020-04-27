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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.senac.projetointegrador.util.AndroidUtils;
import br.com.senac.projetointegrador.util.NetworkUtils;

public class EditProfileActivity extends Activity {

    private EditText textoNomeAlterar, textoEmailAlterar, textoSenhaAlterar;
    private ImageButton imagemOlho;
    private Button botaoAlterar;
    private String campoNome, campoSenha, campoEmail;
    private int count;
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
        imagemOlho = findViewById(R.id.imagemOlhoEdit);

        pegarDadosConta();

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

    public void verSenha(View view)
    {
        if (count % 2 ==0) {
            textoSenhaAlterar.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imagemOlho.setImageResource(R.drawable.olhoriscado);
        } else {
            textoSenhaAlterar.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imagemOlho.setImageResource(R.drawable.olhonormal);
        }
        count++;
    }

    public void pegarDadosConta()
    {
        try {
            int id = AndroidUtils.getCache(this).getInt("usuario_id", 0);
            String ids = String.valueOf(id);
            System.out.println(ids);
            String userID = NetworkUtils.sqlGet(NetworkUtils.TABLE_USERS, "usuario_id", ids);
            JSONObject user_js = NetworkUtils.parseDataBase(userID, 0);
            JSONArray json = new JSONArray(userID);

            textoNomeAlterar.setText(user_js.getString("usuario_nome"));
            textoSenhaAlterar.setText(user_js.getString("usuario_senha"));
            textoEmailAlterar.setText(user_js.getString("usuario_email"));

        } catch (JSONException e)
        {

        }
    }
}
