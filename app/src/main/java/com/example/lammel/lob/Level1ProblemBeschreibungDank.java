package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level1ProblemBeschreibungDank extends AppCompatActivity implements View.OnClickListener{

    private Button problemBeschreibungDank_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_problem_beschreibung_dank);
        this.setTitle("LOB - Das Problem");
        problemBeschreibungDank_Button = (Button) findViewById(R.id.problem_BeschreibungDank_Button);
        problemBeschreibungDank_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Level1Zieldefinition.class));
    }
}
