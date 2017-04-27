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

public class Level2ZieldefinitionNeu extends AppCompatActivity implements View.OnClickListener {

    private Button zieldefinition_zielButton, zurueckButton;
    private int wegCounter;
    private String ziel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hier ein dein Ziel lautet: ?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_zieldefinition_neu);
        this.setTitle("LOB - Dein Ziel");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        ziel = Level1Zieldefinition.getZiel();
        wegCounter = getIntent().getExtras().getInt("WegCounter");
        zurueckButton = (Button) findViewById(R.id.zieldefinitionNeu_zurueckButton);
        zurueckButton.setOnClickListener(this);
        zieldefinition_zielButton = (Button) findViewById(R.id.zieldefinitionNeu_zielButton);
        zieldefinition_zielButton.setEnabled(false);
        zieldefinition_zielButton.setOnClickListener(this);
        final EditText txt = (EditText) findViewById(R.id.zieldefinitionNeu_EditText);
        txt.setHint(ziel);
        txt.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt.length() == 0)
                    zieldefinition_zielButton.setEnabled(false); //disable button if no text entered
                else
                    ziel = txt.getText().toString();
                    zieldefinition_zielButton.setEnabled(true);  //otherwise enabled

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
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
            case R.id.ziel:
                startActivity(new Intent(this, Level1Zieldefinition.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zieldefinitionNeu_zielButton:
                Level1Zieldefinition.setZiel(ziel);
                Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                intent.putExtra("WegCounter", wegCounter);
                startActivity(intent);
                break;

            case R.id.zieldefinitionNeu_zurueckButton:
                Intent intent2 = new Intent(v.getContext(), Level2NeuerWeg.class);
                intent2.putExtra("WegCounter", wegCounter);
                startActivity(intent2);
                break;

            default:
                break;
        }
    }
}
