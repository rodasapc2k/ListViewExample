package com.rodasapc.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String>{


    //Construtores p String[] e ArrayList
    public MyAdapter(Context context, String[] values) {
        super(context, R.layout.row_layout_2, values);
    }

    public MyAdapter(Context context, ArrayList<String> values){
        super(context, R.layout.row_layout_2, values);
    }


    //--------------//

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
        Layout Inflater - transforma xml em objecto
            esta view transforma varios parametros contidos num array num so componente que e percorrido pela lista.
            é bue nice. :)
         */

        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.row_layout_1, parent, false);



        //Cria textview, procura propriedades xml pelo id
        TextView theTextView = (TextView) theView.findViewById(R.id.textView1);

        //Procura o id no array de strings e coloca-os na textview
        String tvShow = getItem(position);
        theTextView.setText(tvShow);

        //Cria imageview e coloca uma imagem do ../res/drawable/
        ImageView theImageView = (ImageView) theView.findViewById(R.id.imageView1);
        theImageView.setImageResource(R.drawable.arrow);

        return theView;
    }
}
