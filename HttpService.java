package com.studio.parselistview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, String> {

    private final String idioma;


    public HttpService(String idioma) {
        this.idioma = idioma;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

            try {
                URL url = new URL("https://restcountries.eu/rest/v2/lang/" + idioma);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //Paises paises = new Gson().fromJson(resposta.toString(), Paises.class);

        return resposta.toString();
    }

    protected static Bitmap BackgroundIMG(String url) throws IOException {

        URL url1 = new URL(url);
        HttpURLConnection conexao = (HttpURLConnection) url1.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.connect();

        InputStream is = conexao.getInputStream();
        Bitmap imagem = BitmapFactory.decodeStream(is);
        return imagem;

    }
}
