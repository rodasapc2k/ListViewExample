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
        setContentView(R.layout.activity_main);



        ListAdapter theAdapter = new MyAdapter(this, arTV);

        //Add xml a ListView e set o MyAdapter.class
        ListView theListView = (ListView) findViewById(R.id.listView1);


        //Cria a view do footer e add
        View footer = getLayoutInflater().inflate(R.layout.row_layout_2, null);


        //TODO: Mete o click listener clickable. (Programa crasha quando clico num show depois de clicar no footer)

        final String newShow = "Attack on Titan";

        footer.setClickable(true);
        footer.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        arTV.add(newShow);
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
