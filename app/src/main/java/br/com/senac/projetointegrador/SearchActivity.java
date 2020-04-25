package br.com.senac.projetointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.app.*;

//import androidx.core.app.ActivityOptions;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AndroidUtils.setImmersiveMode(this,true);

        //ANIMAÇÕES
        Explode trans1 = new Explode();
        trans1.setDuration(750);
        Explode trans2 = new Explode();
        trans2.setDuration(300);
        getWindow().setEnterTransition(trans1);
        getWindow().setReturnTransition(trans2);
        getWindow().setExitTransition(trans2);
        getWindow().setReenterTransition(trans1);
        //ANIMAÇÕES

    }

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, null);
        this.startActivity(i, options.toBundle());
        finish();
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
        new edos.widget.Toast(this, R.layout.dialog_search,100).show();
    }

}
