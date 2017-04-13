package com.example.lammel.lob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        problem = (Button) findViewById(R.id.problemButton);
        keinProblem = (Button) findViewById(R.id.keinProblemButton);
        problem.setOnClickListener(this);
        keinProblem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.keinProblemButton:
                startActivity(new Intent(this, Level1Zieldefinition.class));
                break;

            case R.id.problemButton:
                startActivity(new Intent(this, ProblemBeschreibung.class));
                break;

            default:
                break;
        }
    }

}
