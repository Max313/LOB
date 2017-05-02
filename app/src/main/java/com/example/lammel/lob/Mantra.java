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

public class Mantra extends AppCompatActivity implements View.OnClickListener{


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
    private Button weiter;
    private int source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantra);
        source = getIntent().getExtras().getInt("Source");
        this.setTitle("LOB - Kompliment");
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

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);


        if(source == 0){

            sunyellow.setVisibility(View.VISIBLE);
            sun.setVisibility(View.GONE);
        }
        else{
            sunyellow.setVisibility(View.GONE);
            sun.setVisibility(View.VISIBLE);
        }

        //Button on action
        weiter = (Button) findViewById(R.id.mantraWeiter_Button);
        weiter.setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if(source == 0) {
            menu.findItem(R.id.Sonne).setEnabled(false);
        }
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

            case R.id.Sonne:
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mantraWeiter_Button:
                if(source == 0){
                    startActivity(new Intent(this, SonneDerErkenntnisStart.class));
                    break;
                }

                else if(source == 1){
                    startActivity(new Intent(this, Rueckblick.class));
                    break;

                }

            case R.id.back_Button:
                if(source == 0){
                    startActivity(new Intent(this, Level4InselFragen.class));
                    break;
                }

                else if(source == 1){
                    startActivity(new Intent(this, Sonne8.class));
                    break;
                }

            case R.id.forward_Button:
                if(source == 0){
                    startActivity(new Intent(this, SonneDerErkenntnisStart.class));
                    break;
                }

                else if(source == 1){
                    startActivity(new Intent(this, Rueckblick.class));

                    break;

                }

        }



    }
}
