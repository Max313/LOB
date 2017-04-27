package com.example.lammel.lob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class Level2Ausnahmen extends AppCompatActivity implements View.OnClickListener {

    private Button ausnahmenWeiter;
    private TextView ausnahmenText;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_ausnahmen);
        this.setTitle("LOB - Atolle");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        ausnahmenText = (TextView) findViewById(R.id.ausnahmen_Textview);
        ausnahmenWeiter = (Button) findViewById(R.id.ausnahmen_ButtonWeiter);
        ausnahmenWeiter.setOnClickListener(this);

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
    public void onClick(View v) {
        if (counter == 0){
            ausnahmenText.setText("Gab es in den letzten Wochen irgendwann Zeiten, in denen du den Konflikt weniger schimm erlebt hast?\nWas genau war da anders als sonst?\nWer einen Schatz finden will, muss genau hinschauen.\nNimm dir daher Zeit dich mit den unterschiedlichen Facetten einer Ausnahme auseinanderzusetzen.");
            counter++;
        }
        else if (counter == 1){
            Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
            intent.putExtra("LoesungsCounter", 1);
            startActivity(intent);
            counter = 0;
        }
    }
}
