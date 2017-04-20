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

public class Kompliment extends AppCompatActivity  implements View.OnClickListener{

    public static String FIRSTMESSAGE_KOMPLIMENT;
    public static String SECONDMESSAGE_KOMPLIMENT;
    public static String THIRDMESSAGE_KOMPLIMENT;

    private Button weiter;
    private TextView kompliment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kompliment);
        this.setTitle("LOB - Stärkeinsel - Kompliment");
        weiter = (Button) findViewById(R.id.weiterzuRessource_Button);
        weiter.setOnClickListener(this);

        kompliment = (TextView) findViewById(R.id.komplimentTextView);
        kompliment.setOnClickListener(this);

        final EditText txt = (EditText) findViewById(R.id.kompliment1EditText);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Kompliment.this);
        switch (view.getId()) {
            case R.id.komplimentTextView:

                builder.setTitle("Verhalten");
                builder.setMessage("Gebe dir selbst ein Kompliment, so wie du es auch einem guten Freund geben würdest. \nz.B. Ich gehe umsichtig und überlegt an die Situation heran.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;

            case R.id.weiterzuRessource_Button:
                EditText edit1Text = (EditText) findViewById(R.id.kompliment1EditText);
                FIRSTMESSAGE_KOMPLIMENT = edit1Text.getText().toString();

                EditText edit2Text = (EditText) findViewById(R.id.kompliment2EditText);
                SECONDMESSAGE_KOMPLIMENT = edit2Text.getText().toString();

                EditText edit3Text = (EditText) findViewById(R.id.kompliment3EditText);
                THIRDMESSAGE_KOMPLIMENT = edit3Text.getText().toString();

                startActivity(new Intent(this, Ressource.class));
                break;

            default:
                break;

        }
    }
}
