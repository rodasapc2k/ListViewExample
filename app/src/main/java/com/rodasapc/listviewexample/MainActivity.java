package com.rodasapc.listviewexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    //Show array
    String favTVShows[] = { "Naruto", "One Piece", "Kuroko no Basket", "Tokyo Ghoul",
            "Parasyte - the maxim -", "Nanatsu no Taizai"};

    ArrayList<String> arTV = new ArrayList<>(Arrays.asList(favTVShows));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadActivity();

    }

    private void loadActivity(){

        setContentView(R.layout.activity_main);


        //Cria o ArrayAdapter customizado passando a ArrayList global
        final ListAdapter theAdapter = new MyAdapter(this, arTV);

        //Add xml a ListView e set o MyAdapter.class
        ListView theListView = (ListView) findViewById(R.id.listView1);

        //Cria a view do footer e imbute o xml
        View footer = getLayoutInflater().inflate(R.layout.row_layout_2, null);


        final String newShow = "Attack on Titan";

        footer.setClickable(true);
        footer.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        //a view é clickable
                        Toast.makeText(MainActivity.this, "footer clicked",Toast.LENGTH_LONG).show();
                        arTV.add(newShow);
                        loadActivity();
                        //TODO: Fazer update da ListView depois de add a string

                                    /*  ----------------------------------------
                                        Isto funciona se a string estiver a vir de fora da activity
                                        estes 2 comandos reiniciam por completo a activity. De momemto nao usavel.

                                            finish();
                                            startActivity(getIntent());
                                    ------------------------------------------ */
                    }
                }
        );


        theListView.addFooterView(footer);

        //Set do MyAdapter
        theListView.setAdapter(theAdapter);


        //Quando CLICK num item da toast.
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String tvShowPicked = "You selected " + String.valueOf(adapterView.getItemAtPosition(position));
                Toast.makeText(MainActivity.this, tvShowPicked, Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            MyDialogFragment myFragment = new MyDialogFragment();
            myFragment.show(getFragmentManager(), "myDialog");

            return true;
        }else if(id == R.id.exit_the_app) {

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
