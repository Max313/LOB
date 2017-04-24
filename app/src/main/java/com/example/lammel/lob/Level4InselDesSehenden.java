package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Level4InselDesSehenden extends AppCompatActivity implements View.OnClickListener{

    private ImageView eye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_insel_des_sehenden);
        this.setTitle("LOB - Konsolidierung");



        eye = (ImageView) findViewById(R.id.Auge_imageView);
        eye.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

                startActivity(new Intent(this, Level4InselFragen.class));

    }
}
