package com.example.ungheresefederica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Libro> {

    //variabili d'istanza
    private LayoutInflater inflater;
    private Context ctx;

    //costruttore
    public CustomAdapter(Context context, int id, ArrayList<Libro> libri) {
        super(context,id,libri);
        inflater = LayoutInflater.from(context);
        ctx = context;
    }

    //metodi sovrascritti
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        Libro libro = getItem(position);

        TextView titolo = convertView.findViewById(R.id.titolo);
        TextView autore = convertView.findViewById(R.id.autore);
        ImageView image = convertView.findViewById(R.id.image);

        titolo.setText(libro.getTitolo());
        autore.setText(libro.getAutore());
        image.setImageDrawable(ctx.getResources().getDrawable(R.drawable.libro));

        return convertView;
    }
}
