package com.example.lammel.lob;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ZehnTage extends AppCompatActivity implements View.OnClickListener{

    TextView timer;
    Button fertig;
    CountDownTimer countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zehn_tage);

        fertig = (Button) findViewById(R.id.zehnTage_Button);
        fertig.setEnabled(false);
        fertig.setOnClickListener(this);
        //Timer der 10 Tage runterläuft 864000000 ms
        //Timer der 1 Min runterläuft 60000 ms
        countdown = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = (TextView) findViewById(R.id.zehnTage_Textview2);
                timer.setText("Tage verbleibend: " + (millisUntilFinished / 86400000+1));
            }

            public void onFinish() {
                timer.setText("Geschafft!");
                fertig.setEnabled(true);
            }
        }.start();
;

        //Währenddessen kann man Liste zusammenstellen von Dingen auf die man besonders stolz ist

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
        intent.putExtra("LoesungsCounter", 4);
        startActivity(intent);
    }
}
