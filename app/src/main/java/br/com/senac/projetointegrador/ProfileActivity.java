package br.com.senac.projetointegrador;

import android.content.Intent;
import android.app.*;
import android.os.*;
import android.view.*;

import br.com.senac.projetointegrador.util.AndroidUtils;


public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AndroidUtils.setImmersiveMode(this,true);

    }

    public void irMenuEdit(View view)
    {
        Intent i = new Intent(this, EditProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void irProfile(View view)
    {
        new edos.widget.Toast(this, R.layout.dialog_profile, 100).show();
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
        finish();
    }

    public void irConfigs(View view)
    {
        Intent i = new Intent(this, ConfigActivity.class);
        startActivity(i);
        finish();
    }
}


