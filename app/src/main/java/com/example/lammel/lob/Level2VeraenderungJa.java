package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Level2VeraenderungJa extends AppCompatActivity implements View.OnClickListener {

    private Button veraenderungWeiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_veraenderung_ja);
        this.setTitle("LOB - Atolle");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        veraenderungWeiter = (Button) findViewById(R.id.veraenderungJa_ButtonWeiter);
        veraenderungWeiter.setOnClickListener(this);
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
        Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
        intent.putExtra("LoesungsCounter", 0);
        startActivity(intent);
    }
}
