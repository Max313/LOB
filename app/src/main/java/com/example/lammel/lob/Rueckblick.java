package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Rueckblick extends AppCompatActivity implements View.OnClickListener{

    private SeekBar seekBar;
    private Button weiter;
    private TextView txt;
    private int fortschritt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rueckblick);

        this.setTitle("LOB - Rückblick");

        txt = (TextView) findViewById(R.id.Seek1_TextView);

        seekBar = (SeekBar) findViewById(R.id.rueckblick_seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                fortschritt = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt.setText("" + fortschritt);

            }
        });



        weiter = (Button) findViewById(R.id.rWeiter_Button);
        weiter.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(fortschritt >= 8){
            startActivity(new Intent(this, Ende.class));
        }

        else {
            startActivity(new Intent(this, Neuorientierung.class));
        }


    }
}
