package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class Level2Loesungswege extends AppCompatActivity implements View.OnClickListener{

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
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Some more stuff
    private Button fertig;
    private Button mirFaelltNichtsEin;
    private int loesungsCounter;
    private Boolean txt1leer, txt2leer, txt3leer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_loesungswege);
        this.setTitle("LOB - Atolle");
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
        glowcolor.setVisibility(View.VISIBLE);

        glow = (ImageButton) findViewById(R.id.gluehbirneLeuchtend_Button);
        glow.setVisibility(View.GONE);

        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sungrey.setVisibility(View.VISIBLE);

        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sunyellow.setVisibility(View.GONE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.GONE);

        eins = (TextView) findViewById(R.id.footer1_TextView);
        eins.setVisibility(View.GONE);

        zwei = (TextView) findViewById(R.id.footer2_TextView);
        zwei.setVisibility(View.VISIBLE);

        drei = (TextView) findViewById(R.id.footer3_TextView);
        drei.setVisibility(View.GONE);

        vier = (TextView) findViewById(R.id.footer4_TextView);
        vier.setVisibility(View.GONE);

        fuenf = (TextView) findViewById(R.id.footer5_TextView);
        fuenf.setVisibility(View.GONE);

        //Action and more
        loesungsCounter = getIntent().getExtras().getInt("LoesungsCounter");
        mirFaelltNichtsEin = (Button) findViewById(R.id.loesungswege_ButtonNichts);
        mirFaelltNichtsEin.setOnClickListener(this);
        mirFaelltNichtsEin.setEnabled(false);
        fertig = (Button) findViewById(R.id.loesungswege_ButtonFertig);
        if(loesungsCounter != 5){
            fertig.setEnabled(false);
            mirFaelltNichtsEin.setEnabled(true);
        }
        fertig.setOnClickListener(this);
        enableButton();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.tabelle).setEnabled(false);
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void enableButton(){
        final EditText txt1 = (EditText) findViewById(R.id.loesungswege_edittext1);
        txt1.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt1.length() == 0)
                fertig.setEnabled(false); //disable button if no text entered
                else
                fertig.setEnabled(true);  //otherwise enable

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
        final EditText txt2 = (EditText) findViewById(R.id.loesungswege_edittext2);
        txt2.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt2.length() == 0)
                    fertig.setEnabled(false); //disable button if no text entered
                else
                    fertig.setEnabled(true);  //otherwise enable

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
        final EditText txt3 = (EditText) findViewById(R.id.loesungswege_edittext3);
        txt3.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt3.length() == 0)
                    fertig.setEnabled(false); //disable button if no text entered
                else
                    fertig.setEnabled(true);  //otherwise enable

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loesungswege_ButtonFertig:
                Intent intent1 = new Intent(v.getContext(), Wunderbar.class);
                intent1.putExtra("LoesungsCounter", loesungsCounter);
                startActivity(intent1);
                break;

            case R.id.loesungswege_ButtonNichts:
                if(loesungsCounter == 0){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 0);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 1){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 1);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 2){
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 2);
                    startActivity(intent);
                    break;
                }
               else if(loesungsCounter == 3) {
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 3);
                    startActivity(intent);
                    break;
                }
                else if(loesungsCounter == 4) {
                    Intent intent = new Intent(v.getContext(), Level2NeuerWeg.class);
                    intent.putExtra("WegCounter", 4);
                    startActivity(intent);
                    break;
                }

            case R.id.back_Button:
                startActivity(new Intent(this, Level2Ausnahmen.class));
                break;

            case R.id.ziel_Button:
                startActivity(new Intent(this, Level1Problemdefinition.class));
                break;

            default:
                break;
        }
    }
}
