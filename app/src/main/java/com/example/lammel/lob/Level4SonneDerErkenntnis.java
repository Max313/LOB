package com.example.lammel.lob;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Level4SonneDerErkenntnis extends AppCompatActivity implements View.OnClickListener{

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
    private Button ressource;
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //Buttons and more
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;
    private TextView txt6;
    private TextView txt7;
    private TextView txt8;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;

    private Button start;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_sonne_der_erkenntnis);
        this.setTitle("LOB - Sonne der Erkenntnis");

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

        ziel = (Button) findViewById(R.id.ziel_Button);
        ziel.setOnClickListener(this);

        glowgrey = (ImageButton) findViewById(R.id.gluehbirneDurchsichtig_Button);
        glowgrey.setVisibility(View.GONE);

        glowcolor = (ImageButton) findViewById(R.id.gluehbirneDunkel_Button);
        glowcolor.setVisibility(View.GONE);

        glow = (ImageButton) findViewById(R.id.gluehbirneLeuchtend_Button);
        glow.setVisibility(View.VISIBLE);
        glow.setOnClickListener(this);

        ressource = (Button) findViewById(R.id.ressourcen_Button);
        ressource.setOnClickListener(this);


        sungrey = (ImageButton) findViewById(R.id.sonneGrau_Button);
        sungrey.setVisibility(View.GONE);

        sunyellow = (ImageButton) findViewById(R.id.sonneLeer_Button);
        sunyellow.setVisibility(View.VISIBLE);

        sun = (ImageButton) findViewById(R.id.sonneLeuchtend_Button);
        sun.setVisibility(View.GONE);

        eins = (TextView) findViewById(R.id.footer1_TextView);
        eins.setVisibility(View.GONE);

        zwei = (TextView) findViewById(R.id.footer2_TextView);
        zwei.setVisibility(View.GONE);

        drei = (TextView) findViewById(R.id.footer3_TextView);
        drei.setVisibility(View.GONE);

        vier = (TextView) findViewById(R.id.footer4_TextView);
        vier.setVisibility(View.VISIBLE);

        fuenf = (TextView) findViewById(R.id.footer5_TextView);
        fuenf.setVisibility(View.GONE);

        //Buttons and more on action
        txt1 = (TextView) findViewById(R.id.sonne1);
        txt1.setOnClickListener(this);

        img1 = (ImageView) findViewById(R.id.Sonne1_imageView);
        img1.setOnClickListener(this);

        txt2 = (TextView) findViewById(R.id.sonne2);
        txt2.setOnClickListener(this);

        img2 = (ImageView) findViewById(R.id.Sonne2_imageView);
        img2.setOnClickListener(this);

        txt3 = (TextView) findViewById(R.id.sonne3);
        txt3.setOnClickListener(this);

        img3 = (ImageView) findViewById(R.id.Sonne3_imageView);
        img3.setOnClickListener(this);

        txt4 = (TextView) findViewById(R.id.sonne4);
        txt4.setOnClickListener(this);

        img4 = (ImageView) findViewById(R.id.Sonne4_imageView);
        img4.setOnClickListener(this);

        txt5 = (TextView) findViewById(R.id.sonne5);
        txt5.setOnClickListener(this);

        img5 = (ImageView) findViewById(R.id.Sonne5_imageView);
        img5.setOnClickListener(this);

        txt6 = (TextView) findViewById(R.id.sonne6);
        txt6.setOnClickListener(this);

        img6 = (ImageView) findViewById(R.id.Sonne6_imageView);
        img6.setOnClickListener(this);

        txt7 = (TextView) findViewById(R.id.sonne7);
        txt7.setOnClickListener(this);

        img7 = (ImageView) findViewById(R.id.Sonne7_imageView);
        img7.setOnClickListener(this);

        txt8 = (TextView) findViewById(R.id.sonne8);
        txt8.setOnClickListener(this);

        img8 = (ImageView) findViewById(R.id.Sonne8_imageView);
        img8.setOnClickListener(this);

        start = (Button) findViewById(R.id.startTour_Button);
        start.setOnClickListener(this);
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

        switch (view.getId()){

            case R.id.startTour_Button:
                intent = new Intent(view.getContext(), Sonne1.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            case R.id.sonne1:
                intent = new Intent(view.getContext(), Sonne1.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne1_imageView:
                intent = new Intent(view.getContext(), Sonne1.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.sonne2:
                intent = new Intent(view.getContext(), Sonne2.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne2_imageView:
                intent = new Intent(view.getContext(), Sonne2.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.sonne3:
                intent = new Intent(view.getContext(), Sonne3.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne3_imageView:
                intent = new Intent(view.getContext(), Sonne3.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.sonne4:
                intent = new Intent(view.getContext(), Sonne4.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne4_imageView:
                intent = new Intent(view.getContext(), Sonne4.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.sonne5:
                intent = new Intent(view.getContext(), Sonne5.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne5_imageView:
                intent = new Intent(view.getContext(), Sonne5.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.sonne6:
                intent = new Intent(view.getContext(), Sonne6.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne6_imageView:
                intent = new Intent(view.getContext(), Sonne6.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.sonne7:
                intent = new Intent(view.getContext(), Sonne7.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne7_imageView:
                intent = new Intent(view.getContext(), Sonne7.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.sonne8:
                intent = new Intent(view.getContext(), Sonne8.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne8_imageView:
                intent = new Intent(view.getContext(), Sonne8.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.back_Button:
                startActivity(new Intent(this, SonneDerErkenntnisStart.class));
                break;

            case R.id.forward_Button:
                intent = new Intent(view.getContext(), Sonne1.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            case R.id.ziel_Button:
                startActivity(new Intent(this, Level1Problemdefinition.class));
                break;

            case R.id.gluehbirneLeuchtend_Button:
                startActivity(new Intent(this, Level2Veraenderung.class));
                break;

            case R.id.ressourcen_Button:
                Intent intent = new Intent(view.getContext(), Staerkeinsel.class);
                intent.putExtra("LoesungsCounter", 0);
                startActivity(intent);
                break;

            default:
                break;

        }


    }
}
