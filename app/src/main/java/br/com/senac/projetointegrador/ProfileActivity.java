package br.com.senac.projetointegrador;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.app.*;
import android.os.*;
import android.transition.Visibility;
import android.widget.*;
import android.view.*;
import android.widget.EditText;

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
        Toast.makeText(getApplicationContext(),"Você já está em conta", Toast.LENGTH_SHORT).show();
    }

    public void irHome(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void irBusca(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}


