package com.example.lammel.lob;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Level2NeuerWeg extends AppCompatActivity implements View.OnClickListener {

    private Button neuerWeg;
    private Button neuesZiel;
    private int wegCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_neuer_weg);
        this.setTitle("LOB - Atolle");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        wegCounter = getIntent().getExtras().getInt("WegCounter");
        neuerWeg = (Button) findViewById(R.id.neuerWeg_ButtonLoesung);
        neuerWeg.setOnClickListener(this);
        neuesZiel = (Button) findViewById(R.id.neuerWeg_ButtonZiel);
        neuesZiel.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.neuerWeg_ButtonLoesung:
                //der nächste Lösungsweg wird gestartet
                if(wegCounter==0){
                    startActivity(new Intent(this, Level2Ausnahmen.class));
                    break;
                }
                else if(wegCounter==1){
                    startActivity(new Intent(this, Level2HypoLoesung.class));
                    break;
                }
                else if(wegCounter==2){
                    startActivity(new Intent(this, Level2Universalloesung.class));
                    break;
                }
                else if(wegCounter==3){
                    startActivity(new Intent(this, Level2Exitstrategie.class));
                    break;
                }
                else if(wegCounter==4){
                    //finaler Screen, keine weiteren Lösungswege -> App wahrscheinlich nicht die richtige Lösung
                    startActivity(new Intent(this, Level2KeineLoesung.class));
                    break;
                }

            case R.id.neuerWeg_ButtonZiel:
                //Ziel wird geändert
                Intent intent = new Intent(v.getContext(), Level2ZieldefinitionNeu.class);
                intent.putExtra("WegCounter", wegCounter);
                startActivity(intent);
                break;

            default:
                break;
    }

}

}
