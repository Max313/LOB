package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2UniversalloesungWeiter extends AppCompatActivity implements View.OnClickListener{

    Button universalloesungWeiter_Weiter, universalloesungWeiter_Nichts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_universalloesung_weiter);
        this.setTitle("LOB - Atolle");
        universalloesungWeiter_Weiter = (Button) findViewById(R.id.universalWeiter_ButtonWeiter);
        universalloesungWeiter_Weiter.setOnClickListener(this);
        universalloesungWeiter_Nichts = (Button) findViewById(R.id.universal_Nichts);
        universalloesungWeiter_Nichts.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.universalWeiter_ButtonWeiter:
                startActivity(new Intent(this, Level2Exitstrategie.class));
                break;

            case R.id.universal_Nichts:
                startActivity(new Intent(this, Level2UniversalloesungAnekdote.class));
                break;

            default:
                break;
        }
    }
}
