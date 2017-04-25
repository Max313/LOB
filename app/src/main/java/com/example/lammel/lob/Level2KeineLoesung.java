package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2KeineLoesung extends AppCompatActivity implements View.OnClickListener {

    private Button keineLoseungWeiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_keine_loesung);
        this.setTitle("LOB - Atolle");
        keineLoseungWeiter = (Button) findViewById(R.id.keineLoesung_ButtonWeiter);
        keineLoseungWeiter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
        intent.putExtra("LoesungsCounter", 5);
        startActivity(intent);
    }
}
