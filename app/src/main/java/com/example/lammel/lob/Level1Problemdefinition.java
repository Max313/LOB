package com.example.lammel.lob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by lammel on 11.04.17.
 */
public class Level1Problemdefinition extends AppCompatActivity implements View.OnClickListener{

    private Button problem;
    private Button keinProblem;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1_problemdefinition);
        this.setTitle("LOB - Das Problem");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        problem = (Button) findViewById(R.id.problemButton);
        keinProblem = (Button) findViewById(R.id.keinProblemButton);
        problem.setOnClickListener(this);
        keinProblem.setOnClickListener(this);
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
            case R.id.keinProblemButton:
                startActivity(new Intent(this, Level1Zieldefinition.class));
                break;

            case R.id.problemButton:
                startActivity(new Intent(this, Level1_ProblemBeschreibung.class));
                break;

            default:
                break;
        }
    }

}
