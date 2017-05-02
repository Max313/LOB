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

public class Level4InselFragen extends AppCompatActivity implements View.OnClickListener{

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton sungrey;
    private ImageButton sunyellow;
    private ImageButton sun;
    private ImageButton glowgrey;
    private ImageButton glowcolor;
    private ImageButton glow;

    //Buttons and more
    private int counter = 0;
    private Button weiter;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_insel_fragen);
        this.setTitle("LOB - Insel des Sehenden - 1/4");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);

        glowgrey = (ImageButton) findViewById(R.id.gluehbirneDurchsichtig_Button);
        glowgrey.setVisibility(View.GONE);

        glowcolor = (ImageButton) findViewById(R.id.gluehbirneDunkel_Button);
        glowcolor.setVisibility(View.GONE);

        glow = (ImageButton) findViewById(R.id.gluehbirneLeuchtend_Button);
        glow.setVisibility(View.VISIBLE);

        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sungrey.setVisibility(View.GONE);

        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sunyellow.setVisibility(View.VISIBLE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.GONE);

        //Button on action
        weiter = (Button) findViewById(R.id.frage1_Button);
        weiter.setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.Sonne).setEnabled(false);
        return true;
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        txt = (TextView) findViewById(R.id.frage1_TextView);

        switch(view.getId()){
            case R.id.frage1_Button:
                if(counter == 0){
                    this.setTitle("LOB - Insel des Sehenden - 2/4");
                    txt.setText("Was ist inzwischen geschehen, von dem du möchtest, dass es weiterhin in dieser Weise geschieht?");
                    counter++;
                    break;
                }

                else if(counter == 1){
                    this.setTitle("LOB - Insel des Sehenden - 3/4");

                    txt.setText("Was hat sich seither schon an Positiven entwickelt, so dass du jetzt das Gefühl hast, einen Schritt weiter zu sein?");
                    counter++;
                    break;
                }

                else if(counter == 2) {
                    this.setTitle("LOB - Insel des Sehenden - 4/4");
                    txt.setText("Was von dem, was sich seit der letzten Nutzung der App verändert hat, hältst du für am wichtigsten?");
                    counter++;
                    break;
                }
                else if(counter == 3){
                    counter = 0;
                    Intent intent = new Intent(view.getContext(), Mantra.class);
                    intent.putExtra("Source", 0);
                    startActivity(intent);
                    break;
                }

            case R.id.back_Button:

                if(counter == 0){
                    startActivity(new Intent(this, Level4InselDesSehenden.class));
                    break;
                }
                else if(counter == 1){
                    startActivity(new Intent(this, Level4InselFragen.class));
                    counter--;
                    break;
                }
                else if(counter == 2){
                    this.setTitle("LOB - Insel des Sehenden - 2/4");
                    txt.setText("Was ist inzwischen geschehen, von dem du möchtest, dass es weiterhin in dieser Weise geschieht?");
                    counter--;
                    break;
                }

                else if(counter == 3) {
                    this.setTitle("LOB - Insel des Sehenden - 3/4");
                    txt.setText("Was hat sich seither schon an Positiven entwickelt, so dass du jetzt das Gefühl hast, einen Schritt weiter zu sein?");
                    counter--;
                    break;
                }


            case R.id.forward_Button:

                if(counter == 0){
                    this.setTitle("LOB - Insel des Sehenden - 2/4");
                    txt.setText("Was ist inzwischen geschehen, von dem du möchtest, dass es weiterhin in dieser Weise geschieht?");
                    counter++;
                    break;
                }

                else if(counter == 1){
                    this.setTitle("LOB - Insel des Sehenden - 3/4");

                    txt.setText("Was hat sich seither schon an Positiven entwickelt, so dass du jetzt das Gefühl hast, einen Schritt weiter zu sein?");
                    counter++;
                    break;
                }

                else if(counter == 2) {
                    this.setTitle("LOB - Insel des Sehenden - 4/4");
                    txt.setText("Was von dem, was sich seit der letzten Nutzung der App verändert hat, hältst du für am wichtigsten?");
                    counter++;
                    break;
                }
                else if(counter == 3){
                    counter = 0;
                    Intent intent = new Intent(view.getContext(), Mantra.class);
                    intent.putExtra("Source", 0);
                    startActivity(intent);
                    break;
                }

        }



    }
}
