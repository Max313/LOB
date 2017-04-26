package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Sonne1 extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;
    private Button uebersicht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne1);
        this.setTitle("LOB - Sonne der Erkenntnis 1/8");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        weiter = (Button) findViewById(R.id.Weiter1_Button);
        weiter.setOnClickListener(this);

        uebersicht = (Button) findViewById(R.id.zurUebersicht1_Button);
        uebersicht.setOnClickListener(this);
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

        switch(view.getId()) {

            case R.id.Weiter1_Button:
                startActivity(new Intent(this, Sonne2.class));
                break;

            case R.id.zurUebersicht1_Button:
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
                break;

            default:
                break;

        }


    }
}
