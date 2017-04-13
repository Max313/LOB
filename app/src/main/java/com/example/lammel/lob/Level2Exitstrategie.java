package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2Exitstrategie extends AppCompatActivity implements View.OnClickListener {

    private Button exitstrategie_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_exitstrategie);
        exitstrategie_Button = (Button) findViewById(R.id.exitstrategie_Button);
        exitstrategie_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //hier gehts weiter
        startActivity(new Intent(this, Wunderbar.class));
    }
}
