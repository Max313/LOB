package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Level1_ProblemBeschreibung extends AppCompatActivity implements View.OnClickListener {

    private Button weiterButtonProblem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_beschreibung);
        this.setTitle("LOB - Das Problem");
        weiterButtonProblem = (Button) findViewById(R.id.weiter_buttonProblem);
        weiterButtonProblem.setEnabled(false);
        weiterButtonProblem.setOnClickListener(this);
        final EditText txt = (EditText) findViewById(R.id.problem_editText);
        txt.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt.length() == 0)
                    weiterButtonProblem.setEnabled(false); //disable button if no text entered
                else
                    weiterButtonProblem.setEnabled(true);  //otherwise enable

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Level1ProblemBeschreibungDank.class));
    }
}