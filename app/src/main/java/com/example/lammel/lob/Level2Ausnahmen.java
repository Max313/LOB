package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level2Ausnahmen extends AppCompatActivity implements View.OnClickListener {

    private Button ausnahmenWeiter;
    private TextView ausnahmenText;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_ausnahmen);
        ausnahmenText = (TextView) findViewById(R.id.ausnahmen_Textview);
        ausnahmenWeiter = (Button) findViewById(R.id.ausnahmen_ButtonWeiter);
        ausnahmenWeiter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (counter == 0){
            ausnahmenText.setText("Gab es in den letzten Wochen irgendwann Zeiten, in denen du den Konflikt weniger schimm erlebt hast?\nWas genau war da anders als sonst?\nWer einen Schatz finden will, muss genau hinschauen.\nNimm dir daher Zeit dich mit den unterschiedlichen Facetten einer Ausnahme auseinanderzusetzen.");
            counter++;
        }
        else if (counter == 1){
            startActivity(new Intent(this, Level2HypoLoesung.class));
            counter = 0;
        }
    }
}
