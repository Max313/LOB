package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Level4InselDesSehenden extends AppCompatActivity implements View.OnClickListener{

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_insel_des_sehenden);
        this.setTitle("LOB - Insel des Sehenden");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        start = (Button) findViewById(R.id.fragenStart_Button);
        start.setOnClickListener(this);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
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

            case R.id.tabelle:
                startActivity(new Intent(this, UebersichtTable.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View view) {

                startActivity(new Intent(this, Level4InselFragen.class));

    }
}
