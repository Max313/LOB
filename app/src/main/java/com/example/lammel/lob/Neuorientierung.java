package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Neuorientierung extends AppCompatActivity implements View.OnClickListener{

    private Button zuLevel2;
    private Button zumEnde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuorientierung);

        this.setTitle("LOB - Neuorientierung");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        zuLevel2 = (Button) findViewById(R.id.level2_Button);
        zuLevel2.setOnClickListener(this);

        zumEnde = (Button) findViewById(R.id.wEnde_Button);
        zumEnde.setOnClickListener(this);
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

            case R.id.level2_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            case R.id.wEnde_Button:
                startActivity(new Intent(this, Ende.class));
                break;
        }

    }
}
