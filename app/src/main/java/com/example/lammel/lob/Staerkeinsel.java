package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

public class Staerkeinsel extends AppCompatActivity implements View.OnClickListener{

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
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Buttons and more
    private TextView verhalten;
    private TextView kompliment;
    private TextView ressourcen;
    private Button speichern;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staerkeinsel);
        this.setTitle("LOB - Stärkeinsel - Intro");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);

        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);

        glowgrey = (ImageButton) findViewById(R.id.gluehbirneDurchsichtig_Button);
        glowgrey.setVisibility(View.GONE);

        glowcolor = (ImageButton) findViewById(R.id.gluehbirneDunkel_Button);
        glowcolor.setVisibility(View.GONE);

        glow = (ImageButton) findViewById(R.id.gluehbirneLeuchtend_Button);
        glow.setVisibility(View.VISIBLE);

        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sungrey.setVisibility(View.VISIBLE);

        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sunyellow.setVisibility(View.GONE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.GONE);

        eins = (TextView) findViewById(R.id.footer1_TextView);
        eins.setVisibility(View.GONE);

        zwei = (TextView) findViewById(R.id.footer2_TextView);
        zwei.setVisibility(View.GONE);

        drei = (TextView) findViewById(R.id.footer3_TextView);
        drei.setVisibility(View.VISIBLE);

        vier = (TextView) findViewById(R.id.footer4_TextView);
        vier.setVisibility(View.GONE);

        fuenf = (TextView) findViewById(R.id.footer5_TextView);
        fuenf.setVisibility(View.GONE);

        //Buttons and more
        verhalten = (TextView) findViewById(R.id.verhaltenTextView);
        verhalten.setOnClickListener(this);

        kompliment = (TextView) findViewById(R.id.komplimentTextView);
        kompliment.setOnClickListener(this);

        ressourcen = (TextView) findViewById(R.id.ressourcenTextView);
        ressourcen.setOnClickListener(this);

        speichern = (Button) findViewById(R.id.weiterStaerkeButton);
        speichern.setOnClickListener(this);

        table = (TableLayout) findViewById(R.id.tableSmall);
        table.setOnClickListener(this);


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
        AlertDialog.Builder builder = new AlertDialog.Builder(Staerkeinsel.this);
        switch (view.getId()) {
            case R.id.verhaltenTextView:

                builder.setTitle("Verhalten");
                builder.setMessage("Welches Verhalten an dir fällt dir positiv auf und wie würdest du es charakterisieren. \nz.B. Selbst in einer schwierigen Situation versuche ich das beste für mich und die betroffenen Personen zu machen.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;

            case R.id.komplimentTextView:
                builder.setTitle("Kompliment");
                builder.setMessage("Gebe dir selbst ein Kompliment, so wie du es auch einem guten Freund geben würdest. \nz.B. Ich gehe umsichtig und überlegt an die Situation heran.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK = builder.create();
                dialogK.show();
                break;
            case R.id.ressourcenTextView:
                builder.setTitle("Ressourcen");
                builder.setMessage("Ressourcen die du zur Lösung einer schwierigen Situation beitragen. \nz.B. Umsicht");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR = builder.create();
                dialogR.show();
                break;

            case R.id.weiterStaerkeButton:
                startActivity(new Intent(this, Verhalten.class));
                break;


            case R.id.tableSmall:
                startActivity(new Intent(this, Verhalten.class));
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, Wunderbar.class));
                break;

            case R.id.forward_Button:
                startActivity(new Intent(this, Verhalten.class));
                break;

            default:
                break;
        }
    }
}
