package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ressource extends AppCompatActivity implements View.OnClickListener {

    public static String FIRSTMESSAGE_RESSOURCE;
    public static String SECONDMESSAGE_RESSOURCE;
    public static String THIRDMESSAGE_RESSOURCE;

    private Button weiter;
    private TextView ressource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ressource);

        this.setTitle("LOB - Stärkeinsel - Ressourcen");

        weiter = (Button) findViewById(R.id.weiterzuUebersicht_Button);
        weiter.setOnClickListener(this);

        ressource = (TextView) findViewById(R.id.ressourcenTextView);
        ressource.setOnClickListener(this);

        final EditText txt = (EditText) findViewById(R.id.ressource1EditText);
        txt.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt.length() == 0)
                    weiter.setEnabled(false); //disable send button if no text entered
                else
                    weiter.setEnabled(true);  //otherwise enable

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Ressource.this);
        switch (view.getId()) {
            case R.id.ressourcenTextView:

                builder.setTitle("Verhalten");
                builder.setMessage("Ressourcen die du zur Lösung einer schwierigen Situation beitragen. \nz.B. Umsicht");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;
            case R.id.weiterzuUebersicht_Button:

                EditText edit1Text = (EditText) findViewById(R.id.ressource1EditText);
                FIRSTMESSAGE_RESSOURCE = edit1Text.getText().toString();

                EditText edit2Text = (EditText) findViewById(R.id.ressource2EditText);
                SECONDMESSAGE_RESSOURCE = edit2Text.getText().toString();

                EditText edit3Text = (EditText) findViewById(R.id.ressource3EditText);
                THIRDMESSAGE_RESSOURCE = edit3Text.getText().toString();

                startActivity(new Intent(this, UebersichtTable.class));
                break;

            default:
                break;

        }
    }
}
