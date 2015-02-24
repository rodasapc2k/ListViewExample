package com.rodasapc.listviewexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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



        final AlertDialog.Builder popup = new AlertDialog.Builder(this);
        final EditText inputString = new EditText(this);
        inputString.setGravity(Gravity.CENTER);
        popup.setView(inputString);
        popup.setTitle("Add New Show");

        popup.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newShow = inputString.getText().toString();

                arTV.add(newShow);
                loadActivity();
                Toast.makeText(MainActivity.this, "You added " + newShow + "to your List!", Toast.LENGTH_SHORT).show();
            }
        });

        popup.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Clicked CANCEL!", Toast.LENGTH_SHORT).show();
            }
        });

        footer.setClickable(true);
        footer.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        popup.show();
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

        //TODO: Delete items when swiped
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
