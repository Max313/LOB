package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Ende extends AppCompatActivity implements View.OnClickListener{

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;
    private ImageButton sungrey;
    private ImageButton sunyellow;
    private ImageButton sun;
    private ImageButton glowgrey;
    private ImageButton glowcolor;
    private ImageButton glow;
    private Button ziel;
    private Button ressource;
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Buttons and more
    private Button weiter;
    private TextView txt;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ende);

        this.setTitle("LOB - ...und am Ende...");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setVisibility(View.GONE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.VISIBLE);

        ziel = (Button) findViewById(R.id.ziel_Button);
        ziel.setOnClickListener(this);

        glowgrey = (ImageButton) findViewById(R.id.gluehbirneDurchsichtig_Button);
        glowgrey.setVisibility(View.GONE);

        glowcolor = (ImageButton) findViewById(R.id.gluehbirneDunkel_Button);
        glowcolor.setVisibility(View.GONE);

        glow = (ImageButton) findViewById(R.id.gluehbirneLeuchtend_Button);
        glow.setVisibility(View.VISIBLE);
        glow.setOnClickListener(this);

        ressource = (Button) findViewById(R.id.ressourcen_Button);
        ressource.setOnClickListener(this);

        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sungrey.setVisibility(View.GONE);

        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sunyellow.setVisibility(View.GONE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.VISIBLE);
        sun.setOnClickListener(this);

        eins = (TextView) findViewById(R.id.footer1_TextView);
        eins.setVisibility(View.GONE);

        zwei = (TextView) findViewById(R.id.footer2_TextView);
        zwei.setVisibility(View.GONE);

        drei = (TextView) findViewById(R.id.footer3_TextView);
        drei.setVisibility(View.GONE);

        vier = (TextView) findViewById(R.id.footer4_TextView);
        vier.setVisibility(View.GONE);

        fuenf = (TextView) findViewById(R.id.footer5_TextView);
        fuenf.setVisibility(View.VISIBLE);



        //Button and more (in action)
        weiter = (Button) findViewById(R.id.EndeWeiter_Button);
        weiter.setOnClickListener(this);

        txt = (TextView) findViewById(R.id.Ende_TextView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.ziel:
                startActivity(new Intent(this, Level1Zieldefinition.class));
                return true;

            case R.id.tabelle:
                startActivity(new Intent(this, UebersichtTable.class));
                return true;

            case R.id.Sonne:
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.EndeWeiter_Button:
                if(counter == 0){
                    this.setTitle("LOB - Geschenke");
                    txt.setText("Wenn du gleich die Schatzruhe erreichst habe ich darin ein persönliches Geschenk für dich. Denn ich möchte mich für die gute Zusammenarbeit, dein Durchhaltevermögen " +
                            "und deine Kreativität bedanken. Zudem möchte ich dir vorschlagen, dass du dich auch selbst beschenkst. Vielleicht findest du eine Kleinigkeit die für dich deine Kompetenzen " +
                            "symbolisiert. Nimm dir ruhig Zeit bei der Wahl, zelebriere es. Vielleicht ist es ein Bild oder eine Figur, du wirst es sicher erkennen wenn du es siehst.");
                    counter++;
                    break;
                }

                else if(counter == 1) {
                    this.setTitle("LOB - Verabschiedung");
                    txt.setText("Ganz zum Schluss gibt es noch etwas Wichtiges zu sagen: Alles, wirklich alles, was du an Veränderungen erreicht hast, ist ganz allein aus deiner Idee und aus deinen " +
                            "Kräften erwachsen. LOB hat dir nur geholfen, dass du diese Ideen ausformulierst und du deine Fähigkeiten dann auch nutzt. Mach genau so weiter! Denk daran, die Lösung steckt immer schon in DIR!");
                    weiter.setVisibility(View.GONE);
                    counter = 0;
                    break;

                }

            case R.id.back_Button:
                if(counter == 0){
                    startActivity(new Intent(this, Rueckblick.class));
                    break;
                }
                else if(counter == 1){
                    startActivity(new Intent(this, Ende.class));
                    break;
                }

                else if(counter == 2){
                    this.setTitle("LOB - Geschenke");
                    txt.setText("Wenn du gleich die Schatzruhe erreichst habe ich darin ein persönliches Geschenk für dich. Denn ich möchte mich für die gute Zusammenarbeit, dein Durchhaltevermögen " +
                            "und deine Kreativität bedanken. Zudem möchte ich dir vorschlagen, dass du dich auch selbst beschenkst. Vielleicht findest du eine Kleinigkeit die für dich deine Kompetenzen " +
                            "symbolisiert. Nimm dir ruhig Zeit bei der Wahl, zelebriere es. Vielleicht ist es ein Bild oder eine Figur, du wirst es sicher erkennen wenn du es siehst.");
                    counter--;
                    break;

                }

            case R.id.forward_Button:
                    if(counter == 0){
                        this.setTitle("LOB - Geschenke");
                        txt.setText("Wenn du gleich die Schatzruhe erreichst habe ich darin ein persönliches Geschenk für dich. Denn ich möchte mich für die gute Zusammenarbeit, dein Durchhaltevermögen " +
                                "und deine Kreativität bedanken. Zudem möchte ich dir vorschlagen, dass du dich auch selbst beschenkst. Vielleicht findest du eine Kleinigkeit die für dich deine Kompetenzen " +
                                "symbolisiert. Nimm dir ruhig Zeit bei der Wahl, zelebriere es. Vielleicht ist es ein Bild oder eine Figur, du wirst es sicher erkennen wenn du es siehst.");
                        counter++;
                        break;
                    }

                    else if(counter == 1) {
                        this.setTitle("LOB - Verabschiedung");
                        txt.setText("Ganz zum Schluss gibt es noch etwas Wichtiges zu sagen: Alles, wirklich alles, was du an Veränderungen erreicht hast, ist ganz allein aus deiner Idee und aus deinen " +
                                "Kräften erwachsen. LOB hat dir nur geholfen, dass du diese Ideen ausformulierst und du deine Fähigkeiten dann auch nutzt. Mach genau so weiter! Denk daran, die Lösung steckt immer schon in DIR!");
                        weiter.setVisibility(View.GONE);
                        forward.setVisibility(View.GONE);
                        counter = 0;
                        break;
                    }

            case R.id.ziel_Button:
                startActivity(new Intent(this, Level1Problemdefinition.class));
                break;

            case R.id.gluehbirneLeuchtend_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            case R.id.ressourcen_Button:
                startActivity(new Intent(this, Staerkeinsel.class));
                break;

            case R.id.sonneLeuchtend_Button:
                startActivity(new Intent(this, Level4InselDesSehenden.class));
                break;
}



    }
}
