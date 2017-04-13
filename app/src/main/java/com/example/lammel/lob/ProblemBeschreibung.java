package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProblemBeschreibung extends AppCompatActivity implements View.OnClickListener {

    Button weiterButtonProblem;
    TextView problemDank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_beschreibung);
        weiterButtonProblem = (Button) findViewById(R.id.weiter_buttonProblem);
        weiterButtonProblem.setOnClickListener(this);
        problemDank = (TextView) findViewById(R.id.problemDank);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Level1ProblemBeschreibungDank.class));
    }
}
