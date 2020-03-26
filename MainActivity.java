package com.studio.parselistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText idioma;
    private Button pesquisar;
    private ListView lista;

    private final String NAME = "name";
    private final String CAPITAL = "capital";
    private final String REGION = "region";
    private final String FLAG = "flag";

    private List<Paises> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idioma = findViewById(R.id.txtIdioma);
        pesquisar = findViewById(R.id.btnPesquisar);
        lista = findViewById(R.id.lstPaises);


        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpService service = new HttpService(idioma.getText().toString());

                try {
                    String resposta = service.execute().get();

                    if(resposta.equals(""))
                        Toast.makeText(MainActivity.this,"Nenhum resultado encontrado",Toast.LENGTH_SHORT).show();
                    else{

                        resposta = resposta.replace("},{", "#");

                        String[] subString = resposta.split("#");

                        Paises[] paises = new Paises[subString.length] ;

                        for (int i=0; i < subString.length; i++){

                            paises[i] = new Paises();

                            if(subString[i].contains(NAME))
                                paises[i].setName(Carregarobjetos(subString[i],NAME));

                            if(resposta.contains(CAPITAL))
                                paises[i].setCapital(Carregarobjetos(subString[i],CAPITAL));

                            if(resposta.contains(REGION))
                                paises[i].setRegion(Carregarobjetos(subString[i],REGION));

                            if(resposta.contains(FLAG))
                                paises[i].setFlag(Carregarobjetos(subString[i],FLAG));
                        }

                        list = Arrays.asList(paises);

                        ArrayAdapter adapter = new PaisesAdapter(MainActivity.this,list);
                        lista.setAdapter(adapter);

                    }


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private String Carregarobjetos(String resposta, String indice){

        int posicao = resposta.indexOf(":",resposta.indexOf(indice));
        String Resultado = resposta.substring(posicao + 2,resposta.indexOf(",", resposta.indexOf(indice))-1);

        return Resultado;
    }

}

