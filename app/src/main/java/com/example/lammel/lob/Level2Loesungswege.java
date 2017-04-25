package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.security.AccessController.getContext;

public class Level2Loesungswege extends AppCompatActivity implements View.OnClickListener{

    private Button fertig;
    private Button mirFaelltNichtsEin;
    private int loesungsCounter;
    private Boolean txt1leer, txt2leer, txt3leer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_loesungswege);
        this.setTitle("LOB - Atolle");
        loesungsCounter = getIntent().getExtras().getInt("LoesungsCounter");
        fertig = (Button) findViewById(R.id.loesungswege_ButtonFertig);
        if(loesungsCounter != 5){
            fertig.setEnabled(false);
        }
        fertig.setOnClickListener(this);
        enableButton();
        mirFaelltNichtsEin = (Button) findViewById(R.id.loesungswege_ButtonNichts);
        mirFaelltNichtsEin.setOnClickListener(this);
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
                startActivity(new Intent(this, Wunderbar.class));
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

            default:
                break;
        }
    }
}
