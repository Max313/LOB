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

public class Level2UniversalloesungWeiter extends AppCompatActivity implements View.OnClickListener{

    Button universalloesungWeiter_Weiter, universalloesungWeiter_Nichts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_universalloesung_weiter);
        this.setTitle("LOB - Atolle");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        universalloesungWeiter_Weiter = (Button) findViewById(R.id.universalWeiter_ButtonWeiter);
        universalloesungWeiter_Weiter.setOnClickListener(this);
        universalloesungWeiter_Weiter.setEnabled(false);
        universalloesungWeiter_Nichts = (Button) findViewById(R.id.universal_Nichts);
        universalloesungWeiter_Nichts.setOnClickListener(this);

        final EditText txt1 = (EditText) findViewById(R.id.universal_EditText);
        txt1.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                if(txt1.length() == 0)
                    universalloesungWeiter_Weiter.setEnabled(false); //disable button if no text entered
                else
                    universalloesungWeiter_Weiter.setEnabled(true);  //otherwise enable

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.activity_main){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.universalWeiter_ButtonWeiter:
                Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
                intent.putExtra("LoesungsCounter", 3);
                startActivity(intent);
                break;

            case R.id.universal_Nichts:
                startActivity(new Intent(this, Level2UniversalloesungAnekdote.class));
                break;

            default:
                break;
        }
    }
}
