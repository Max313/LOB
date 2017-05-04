package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Level4SonneDerErkenntnis extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Footer Buttons
    private ImageButton back;
    private ImageButton forward;
    private ImageButton forwardDisabled;


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

    //Buttons and more
    private Button start;
    private Button fertig;
    private Intent intent;
    private int source;
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_sonne_der_erkenntnis);
        this.setTitle("LOB - Sonne der Erkenntnis");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level4_sonne_der_erkenntnis, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level4_sonne_der_erkenntnis);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);


        //Footer Buttons
        back = (ImageButton) findViewById(R.id.back_Button);
        back.setOnClickListener(this);

        forward = (ImageButton) findViewById(R.id.forward_Button);
        forward.setOnClickListener(this);
        forward.setVisibility(View.VISIBLE);

        forwardDisabled = (ImageButton) findViewById(R.id.forwardgrey_Button);
        forwardDisabled.setVisibility(View.GONE);


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

        //Button and more action
        start = (Button) findViewById(R.id.startTour_Button);

        fertig = (Button) findViewById(R.id.fertig_Button);

        source = getIntent().getExtras().getInt("Source");
        if(source == 0){
            start.setVisibility(View.VISIBLE);
            start.setOnClickListener(this);
            fertig.setVisibility(View.GONE);
        }
        else{
            fertig.setVisibility(View.VISIBLE);
            fertig.setOnClickListener(this);
            start.setVisibility(View.GONE);
        }
    }

    //Welche Menüoptionen sind enabled
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        saved = getSharedPreferences(PREFS_NAME, 0);

        if (!saved.getBoolean("MenuZiel", false)){
            menu.findItem(R.id.ziel).setEnabled(false);
        }
        if (!saved.getBoolean("MenuTabelle", false)){
            menu.findItem(R.id.tabelle).setEnabled(false);
        }
        if (!saved.getBoolean("MenuSonne", false)) {
            menu.findItem(R.id.Sonne).setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Menüaktivität
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.ziel:
                startActivity(new Intent(this, MenuZiel.class));
                return true;

            case R.id.tabelle:
                startActivity(new Intent(this, UebersichtTable.class));
                return true;

            case R.id.Sonne:
                startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
                return true;

            case R.id.Hausaufgabe:
                startActivity(new Intent(this, MenuHausaufgabe.class));
                return true;

            case R.id.action_delete:
                editor.clear();
                editor.apply();
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
                if(source == 0){
                    startActivity(new Intent(this, SonneDerErkenntnisStart.class));
                    break;
                }

                else if(source == 1){
                    intent = new Intent(view.getContext(), Sonne1.class);
                    intent.putExtra("Tour", false);
                    startActivity(intent);
                    break;
                }

                else if(source == 2){
                    intent = new Intent(view.getContext(), Sonne2.class);
                    intent.putExtra("Tour", false);
                    startActivity(intent);
                    break;
                }

                else if(source == 3){
                    intent = new Intent(view.getContext(), Sonne3.class);
                    intent.putExtra("Tour", false);
                    startActivity(intent);
                    break;
                }

                else if(source == 4){
                    intent = new Intent(view.getContext(), Sonne4.class);
                    intent.putExtra("Tour", false);
                    startActivity(intent);
                    break;
                }

                else if(source == 5){
                    intent = new Intent(view.getContext(), Sonne5.class);
                    intent.putExtra("Tour", false);
                    startActivity(intent);
                    break;
                }

                else if(source == 6){
                    intent = new Intent(view.getContext(), Sonne6.class);
                    intent.putExtra("Tour", false);
                    startActivity(intent);
                    break;
                }

                else if(source == 7){
                    intent = new Intent(view.getContext(), Sonne7.class);
                    intent.putExtra("Tour", false);
                    startActivity(intent);
                    break;
                }

                else if(source == 8){
                    intent = new Intent(view.getContext(), Sonne8.class);
                    intent.putExtra("Tour", false);
                    startActivity(intent);
                    break;
                }

            case R.id.fertig_Button:
                Intent intent = new Intent(view.getContext(), Mantra.class);
                intent.putExtra("Source", 1);
                startActivity(intent);

            case R.id.forward_Button:
                intent = new Intent(view.getContext(), Sonne1.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            default:
                break;

        }
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        editor.putBoolean("MenuSonne", true);
        editor.apply();

    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}
