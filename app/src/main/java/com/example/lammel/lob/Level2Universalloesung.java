package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Level2Universalloesung extends AppCompatActivity implements View.OnClickListener {

    private Button universalWeiter;
    private Button universalAnekdote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_universalloesung);
        this.setTitle("LOB - Atolle");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        universalWeiter = (Button) findViewById(R.id.universal_ButtonWeiter);
        universalWeiter.setOnClickListener(this);
        universalAnekdote = (Button) findViewById(R.id.universal_Anekdote);
        universalAnekdote.setOnClickListener(this);
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
            case R.id.universal_ButtonWeiter:
                startActivity(new Intent(this, Level2UniversalloesungWeiter.class));
                break;

            case R.id.universal_Anekdote:
                startActivity(new Intent(this, Level2UniversalloesungAnekdote.class));
                break;

            default:
                break;
        }
    }
}
