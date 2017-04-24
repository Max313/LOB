package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2VeraenderungJa extends AppCompatActivity implements View.OnClickListener {

    private Button veraenderungWeiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_veraenderung_ja);
        this.setTitle("LOB - Atolle");
        veraenderungWeiter = (Button) findViewById(R.id.veraenderungJa_ButtonWeiter);
        veraenderungWeiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
        intent.putExtra("LoesungsCounter", 0);
        startActivity(intent);
    }
}
