package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Level1ZielVerwahren extends AppCompatActivity implements View.OnClickListener {

    Button zielVerwahren_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_zielverwahren);
        this.setTitle("LOB - Dein Ziel");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        zielVerwahren_Button = (Button) findViewById(R.id.zielVerwahren_Button);
        zielVerwahren_Button.setOnClickListener(this);
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
        //startActivity level 2
        startActivity(new Intent(this, Level2Veraenderung.class));
    }
}
