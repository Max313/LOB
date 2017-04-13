package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2Loesungswege extends AppCompatActivity implements View.OnClickListener{

    private Button fertig;
    private Button mirFaelltNichtsEin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_loesungswege);
        this.setTitle("LOB - Atolle");
        fertig = (Button) findViewById(R.id.loesungswege_ButtonFertig);
        fertig.setOnClickListener(this);
        mirFaelltNichtsEin = (Button) findViewById(R.id.loesungswege_ButtonNichts);
        mirFaelltNichtsEin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loesungswege_ButtonFertig:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            case R.id.loesungswege_ButtonNichts:
                // oder Dialog?
                startActivity(new Intent(this, ProblemBeschreibung.class));
                break;

            default:
                break;
        }
    }
}
