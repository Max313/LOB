package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProblemBeschreibung extends AppCompatActivity implements View.OnClickListener {

    int counter = 0;
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
        if(counter == 0){
            problemDank.setText("Vielen Dank für deine Offenheit!\nEin Problem ist häufig sehr belastend und nimmt gedanklich viel Platz ein, raubt Energie und trübt den Blick auf das Positive.\nLass uns einen ersten kleinen Versuch starten.\nWir nehmen das Problem nun an uns und verwahren es für dich gut auf.\nDu kannst natürlich jederzeit darauf zugreifen, aber nun bist du erstmal frei.\nDie belastenden Gedanken liegen an einem anderen Ort und du hast Platz für Neues.");
            counter++;
        }
        else if(counter == 1){
            startActivity(new Intent(this, Level1Zieldefinition.class));
            counter = 0;
        }
    }
}
