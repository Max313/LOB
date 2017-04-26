package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Mantra extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;
    private int source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantra);
        source = getIntent().getExtras().getInt("Source");
        this.setTitle("LOB - Kompliment");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        weiter = (Button) findViewById(R.id.mantraWeiter_Button);
        weiter.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.activity_main){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        if(source == 0){
            startActivity(new Intent(this, SonneDerErkenntnisStart.class));
        }

        else if(source == 1){
            startActivity(new Intent(this, Rueckblick.class));

        }

    }
}
