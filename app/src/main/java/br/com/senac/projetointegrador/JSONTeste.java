package br.com.senac.projetointegrador;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

import br.com.senac.projetointegrador.util.NetworkUtils;

public class JSONTeste extends Activity {

    TextView mTextViewResult;
    Button button;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonteste);

        mTextViewResult = findViewById(R.id.textViewJSON);
        button = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {
        result = NetworkUtils.sqlGet("series");

        try {
            JSONObject jObject = new JSONObject(NetworkUtils.sqlGet("series"));
            JSONArray jArray = jObject.getJSONArray("0");

            for (int i=0; i < jArray.length(); i++)
            {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    // Puxando itens do array
                    int id = oneObject.getInt("serie_id");
                    String nomeSerie = oneObject.getString("serie_nome");

                    mTextViewResult.append(id + ", " + nomeSerie + "\n\n");
                } catch (JSONException e) {
                    // Oops
                }
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
