package com.example.ungheresefederica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button insert;
    EditText titolo, autore;
    ListView listViewLibri;
    static ArrayList<Libro> libri = new ArrayList<>();
    Libro daCancellare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inizializzazioni
        titolo = findViewById(R.id.edit_titolo);
        autore = findViewById(R.id.edit_autore);
        insert = findViewById(R.id.insert);
        listViewLibri = findViewById(R.id.listview);

        //verifica se c'è uno stato precedente da restaurare
        if(savedInstanceState!= null){
            titolo.setText(savedInstanceState.getString("title"));
            autore.setText(savedInstanceState.getString("author"));
        }

        // crea e setta l'adapter
        CustomAdapter adapter = new CustomAdapter(this, R.layout.list_item, libri);
        listViewLibri.setAdapter(adapter);

        //listener su bottone inserimento
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEditTexts()){   //se nessuno dei due campi è vuoto aggiunge il libro alla lista
                    libri.add(new Libro(titolo.getText().toString(),autore.getText().toString(),getResources().getDrawable(R.drawable.libro)));
                }
            }
        });

        //listener sull'elemento del listView
        listViewLibri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                daCancellare = (Libro) listViewLibri.getItemAtPosition(position);
                showAlertDialog();
            }
        });
    }

    //crea il dialog per confermare la rimozione del libro
    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Cancella")
                .setMessage("Sei sicuro di voler cancellare il libro dalla lista?")
                .setIcon(android.R.drawable.ic_delete)
                .setPositiveButton("Sì", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //rimuove il libro dalla lista, aggiorna il listview ed notifica l'avvenuta rimozione
                        libri.remove(daCancellare);

                        Toast.makeText(getApplicationContext(), "Rimosso", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton("No", null).show();
    }

    //per non perdere lo stato nelle rotazioni
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //salva i dati inseriti negli edit text
        outState.putString("title", titolo.getText().toString());
        outState.putString("author", autore.getText().toString());
        super.onSaveInstanceState(outState);
    }

    //metodo per il controllo degli edit text
    private boolean checkEditTexts(){
        if(titolo.getText().toString().equals("")){
            titolo.setError("Non lasciare il campo vuoto!");
            return false;
        }
        else if(autore.getText().toString().equals("")){
            autore.setError("Non lasciare il campo vuoto!");
            return false;
        }
        else{
            return true;
        }
    }
}