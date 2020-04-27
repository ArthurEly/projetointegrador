package br.com.senac.projetointegrador;

import android.content.Intent;
import android.app.*;
import android.os.*;
import android.transition.Slide;
import android.view.*;
import android.widget.TextView;

//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityOptionsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.senac.projetointegrador.util.AndroidUtils;
import br.com.senac.projetointegrador.util.NetworkUtils;


public class ProfileActivity extends Activity {

    private TextView textoSenha, textoNome, textoEmail;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        textoSenha = findViewById(R.id.TEXTO_SENHA_PROFILE);
        pegarDadosConta();
    }

	@Override protected void onResume() {
		super.onResume();
        AndroidUtils.setImmersiveMode(this,true);
	}

    public void irMenuEdit(View view) {
//        edos.app.Dialog dialog = new edos.app.Dialog(this, R.layout.autenticacaoprofile);
//        dialog.show();
        senha = textoSenha.getText().toString();

        Intent i = new Intent(this, AutenticacaoProfile.class);
        ActivityOptions activityOptionsCompat = ActivityOptions.makeSceneTransitionAnimation(this,null);
        Bundle b = new Bundle ();
        b.putString("senha", senha);
        i.putExtras(b);

//        startActivity(i);
        startActivity(i, activityOptionsCompat.toBundle());
    }


    public void irProfile(View view)
    {
        new edos.widget.Toast(this, R.layout.dialog_profile, 100).show();
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity(i, options.toBundle());
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        startActivity(i, options.toBundle());
        finish();
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

            textoEmail = findViewById(R.id.TEXTO_EMAIL_PROFILE);
            textoNome = findViewById(R.id.TEXTO_NOME_PROFILE);

            textoNome.setText(user_js.getString("usuario_nome"));
            textoSenha.setText(user_js.getString("usuario_senha"));
            textoEmail.setText(user_js.getString("usuario_email"));

        } catch (JSONException e)
        {

        }
    }

}


