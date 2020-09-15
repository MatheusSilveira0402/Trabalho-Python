package com.example.trabalho;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    TextView textResultados;
    EditText editCodigomunicipio, editAnomes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResultados = findViewById(R.id.textResults);
        editAnomes = findViewById(R.id.editAnomes);
        editCodigomunicipio = findViewById(R.id.editCodigomunicipio);


    }



    public void solicitarDados(View view) {
        for(int i = 1; i < 12;i++) {
        RequestQueue queue = Volley.newRequestQueue(this);
            int o = 0;
            String url = "http://www.transparencia.gov.br/api-de-dados/bolsa-familia-por-municipio?mesAno=" +
                    editAnomes.getText().toString() +
                    o + i +
                    "&codigoIbge="
                    + editCodigomunicipio.getText().toString() +
                    "&pagina=1";

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Bolsafamilia bolsafamilia = new Bolsafamilia();
                            for(int i = 0; i < 3;i++) {

                                String dados = response.toString();
                                bolsafamilia.addBolsa(dados);
                                textResultados.setText(bolsafamilia.string());

                            }
                                textResultados.setText(bolsafamilia.toString());

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textResultados.setText(error.getMessage());
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("chave-api-dados", "3502e34b3d69ddc701a8378a7a18deb0");

                    return params;
                }
            };
            request.setRetryPolicy(new DefaultRetryPolicy(5000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

    }

}