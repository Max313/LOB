package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2Universalloesung extends AppCompatActivity implements View.OnClickListener {

    private Button universalWeiter;
    private Button universalAnekdote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_universalloesung);
        universalWeiter = (Button) findViewById(R.id.universal_ButtonWeiter);
        universalWeiter.setOnClickListener(this);
        universalAnekdote = (Button) findViewById(R.id.universal_Anekdote);
        universalAnekdote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.universal_ButtonWeiter:
                startActivity(new Intent(this, Level2UniversalloesungWeiter.class));
                break;

            case R.id.universal_Anekdote:
                startActivity(new Intent(this, Level2UniversalloesungAnekdote.class));
                break;

            default:
                break;
        }
    }
}
