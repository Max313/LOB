package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Level1Zieldefinition extends AppCompatActivity implements View.OnClickListener{

    private static String ziel;
    private Button zielFesthalten_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_zieldefinition);
        this.setTitle("LOB - Dein Ziel");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        zielFesthalten_Button = (Button) findViewById(R.id.zielFesthalten_Button);
        zielFesthalten_Button.setEnabled(false);
        zielFesthalten_Button.setOnClickListener(this);
        final EditText zieltxt = (EditText) findViewById(R.id.zieldefinition_EditText);
        zieltxt.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(zieltxt.length() == 0)
                    zielFesthalten_Button.setEnabled(false); //disable button if no text entered
                else
                    ziel = zieltxt.getText().toString();
                    setZiel(ziel);
                    zielFesthalten_Button.setEnabled(true);  //otherwise enabled

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.ziel).setEnabled(false);
        menu.findItem(R.id.tabelle).setEnabled(false);
        menu.findItem(R.id.Sonne).setEnabled(false);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Level1ZielVerwahren.class));
    }

    public static void setZiel(String letztesZiel){
        ziel = letztesZiel;
    }
    public static String getZiel(){
        return ziel;
    }
}
