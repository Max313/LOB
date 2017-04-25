package com.example.lammel.lob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Ende extends AppCompatActivity implements View.OnClickListener{

    private Button weiter;
    private TextView txt;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ende);

        this.setTitle("LOB - ...und am Ende...");


        weiter = (Button) findViewById(R.id.EndeWeiter_Button);
        weiter.setOnClickListener(this);

        txt = (TextView) findViewById(R.id.Ende_TextView);


    }

    @Override
    public void onClick(View view) {

        if(counter == 0){
            this.setTitle("LOB - Geschenke");
            txt.setText("Wenn du gleich die Schatzruhe erreichst habe ich darin ein persönliches Geschenk für dich. Denn ich möchte mich für die gute Zusammenarbeit, dein Durchhaltevermögen " +
                    "und deine Kreativität bedanken. Zudem möchte ich dir vorschlagen, dass du dich auch selbst beschenkst. Vielleicht findest du eine Kleinigkeit die für dich deine Kompetenzen " +
                    "symbolisiert. Nimm dir ruhig Zeit bei der Wahl, zelebriere es. Vielleicht ist es ein Bild oder eine Figur, du wirst es sicher erkennen wenn du es siehst.");
            counter++;
        }

        else if(counter == 1) {
            this.setTitle("LOB - Verabschiedung");
            txt.setText("Ganz zum Schluss gibt es noch etwas Wichtiges zu sagen: Alles, wirklich alles, was du an Veränderungen erreicht hast, ist ganz allein aus deiner Idee und aus deinen " +
                    "Kräften erwachsen. LOB hat dir nur geholfen, dass du diese Ideen ausformulierst und du deine Fähigkeiten dann auch nutzt. Mach genau so weiter! Denk daran, die Lösung steckt immer schon in DIR!");
            weiter.setVisibility(View.GONE);
            counter = 0;

        }


    }
}
