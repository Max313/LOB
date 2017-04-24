package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        start = (Button) findViewById(R.id.fragenStart_Button);
        start.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

                startActivity(new Intent(this, Level4InselFragen.class));

    }
}
