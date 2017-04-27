package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Sonne4 extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;
    private Button uebersicht;
    private Boolean tour;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne4);

        this.setTitle("LOB - Sonne der Erkenntnis 4/8");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        tour = getIntent().getExtras().getBoolean("Tour");

        weiter = (Button) findViewById(R.id.Weiter4_Button);
        weiter.setOnClickListener(this);

        uebersicht = (Button) findViewById(R.id.zurUebersicht4_Button);
        uebersicht.setOnClickListener(this);

        if(tour){
            uebersicht.setVisibility(View.GONE);
        }

        else {
            weiter.setVisibility(View.GONE);
        }
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
        switch (view.getId()){
            case R.id.Weiter4_Button:
                intent = new Intent(view.getContext(), Sonne5.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            case R.id.zurUebersicht4_Button:
                intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            default:
                break;
        }

    }
}
