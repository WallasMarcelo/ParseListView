package com.studio.parselistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import static com.studio.parselistview.HttpService.BackgroundIMG;

public class PaisesAdapter extends ArrayAdapter<Paises> {

    private MainActivity context;
    private List<Paises> elementos;

    public PaisesAdapter(MainActivity context, List<Paises> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.linha, parent, false);

        TextView Nome = view.findViewById(R.id.idNome);
        TextView Capital = view.findViewById(R.id.idCapital);
        TextView Region = view.findViewById(R.id.idRegion);
        ImageView Bandeira = view.findViewById(R.id.imgBandeira);


        Nome.setText(elementos.get(position).getName());
        Capital.setText(elementos.get(position).getCapital());
        Region.setText(elementos.get(position).getRegion());

        try {
            Bandeira.setImageBitmap(BackgroundIMG(elementos.get(position).getFlag()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
 }


