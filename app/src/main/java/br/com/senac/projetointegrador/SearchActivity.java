package br.com.senac.projetointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.*;

import br.com.senac.projetointegrador.util.AndroidUtils;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AndroidUtils.setImmersiveMode(this,true);
    }

    public void irProfile(View view)
    {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void irBusca(View view)
    {
        new edos.widget.Toast(this, R.layout.dialog_search,100).show();
    }

    public void irConfigs(View view)
    {
        Intent i = new Intent(this, ConfigActivity.class);
        startActivity(i);
        finish();
    }
}
