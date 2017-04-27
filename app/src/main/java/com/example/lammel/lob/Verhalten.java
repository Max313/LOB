package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;

public class Verhalten extends AppCompatActivity implements View.OnClickListener{

    public static String FIRSTMESSAGE_VERHALTEN;
    public static String SECONDMESSAGE_VERHALTEN;
    public static String THIRDMESSAGE_VERHALTEN;

    private Button weiter;
    private TextView verhalten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verhalten);
        this.setTitle("LOB - Stärkeinsel - Verhalten");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        weiter = (Button) findViewById(R.id.weiterzuKompliment_Button);
        weiter.setEnabled(false);
        weiter.setOnClickListener(this);

        verhalten = (TextView) findViewById(R.id.verhaltenTextView);
        verhalten.setOnClickListener(this);

        final EditText txt = (EditText) findViewById(R.id.verhalten1EditText);
            txt.addTextChangedListener(new TextWatcher()
            {
                public void afterTextChanged(Editable s)
                {
                    if(txt.length() == 0)
                        weiter.setEnabled(false); //disable button if no text entered
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

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Verhalten.this);
        switch (view.getId()) {
            case R.id.verhaltenTextView:

                builder.setTitle("Verhalten");
                builder.setMessage("Welches Verhalten an dir fällt dir positiv auf und wie würdest du es charakterisieren. " +
                        "\nz.B. Selbst in einer schwierigen Situation versuche ich das beste für mich und die betroffenen Personen zu machen.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;


            case R.id.weiterzuKompliment_Button:
                EditText edit1Text = (EditText) findViewById(R.id.verhalten1EditText);
                FIRSTMESSAGE_VERHALTEN = edit1Text.getText().toString();

                EditText edit2Text = (EditText) findViewById(R.id.verhalten2EditText);
                SECONDMESSAGE_VERHALTEN = edit2Text.getText().toString();

                EditText edit3Text = (EditText) findViewById(R.id.verhalten3EditText);
                THIRDMESSAGE_VERHALTEN = edit3Text.getText().toString();

                if (!UebersichtTable.aenderung){
                    startActivity(new Intent(this, Kompliment.class));
                }

                else {
                    UebersichtTable.aenderung = false;
                    startActivity(new Intent(this, UebersichtTable.class));
                }
                break;

            default:
                break;

        }
    }
}
