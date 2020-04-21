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



public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



    }

    public void irMenuEdit(View view)
    {
        Intent i = new Intent(this, EditProfileActivity.class);
        startActivity(i);
    }
}


