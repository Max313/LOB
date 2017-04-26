package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Level2Veraenderung extends AppCompatActivity implements View.OnClickListener{

    Button veraenderungJa, veraenderungNein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_veraenderung);
        this.setTitle("LOB - Atolle");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        veraenderungJa = (Button) findViewById(R.id.veraenderung_ButtonJa);
        veraenderungJa.setOnClickListener(this);
        veraenderungNein = (Button) findViewById(R.id.veraenderung_ButtonNein);
        veraenderungNein.setOnClickListener(this);
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
            case R.id.veraenderung_ButtonJa:
                startActivity(new Intent(this, Level2VeraenderungJa.class));
                break;

            case R.id.veraenderung_ButtonNein:
                //startActivity(new Intent(this, Level2VeraenderungNein.class));
                startActivity(new Intent(this, Level2Ausnahmen.class));
                break;

            default:
                break;
        }
    }
}
