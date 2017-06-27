package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class Level4SonneDerErkenntnis extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons and more
    private ImageView img1;
    private ImageView img1hell;
    private ImageView img2;
    private ImageView img2hell;
    private ImageView img3;
    private ImageView img3hell;
    private ImageView img4;
    private ImageView img4hell;
    private ImageView img5;
    private ImageView img5hell;
    private ImageView img6;
    private ImageView img6hell;
    private ImageView img7;
    private ImageView img7hell;
    private ImageView img8;
    private ImageView img8hell;

    //Buttons and more
    private Button start;
    private Button fertig;
    private Intent intent;
    private int source;
    private int count;
    private AppCompatDelegate delegate;
    private static final String LOG_TAG = "FileTest";


    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_sonne_der_erkenntnis);
        this.setTitle("Sonne der Erkenntnis");

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
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level4));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.sonnevoll);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Buttons on action
        img1 = (ImageView) findViewById(R.id.Sonne1_imageView);
        img1.setVisibility(View.GONE);
        img1.setOnClickListener(this);

        img1hell = (ImageView) findViewById(R.id.SonneHell1_imageView);
        img1hell.setOnClickListener(this);

        img2 = (ImageView) findViewById(R.id.Sonne2_imageView);
        img2.setVisibility(View.GONE);
        img2.setOnClickListener(this);

        img2hell = (ImageView) findViewById(R.id.Sonne2hell_imageView);
        img2hell.setOnClickListener(this);


        img3 = (ImageView) findViewById(R.id.Sonne3_imageView);
        img3.setVisibility(View.GONE);
        img3.setOnClickListener(this);

        img3hell = (ImageView) findViewById(R.id.Sonne3hell_imageView);
        img3hell.setOnClickListener(this);

        img4 = (ImageView) findViewById(R.id.Sonne4_imageView);
        img4.setVisibility(View.GONE);
        img4.setOnClickListener(this);

        img4hell = (ImageView) findViewById(R.id.Sonne4hell_imageView);
        img4hell.setOnClickListener(this);

        img5 = (ImageView) findViewById(R.id.Sonne5_imageView);
        img5.setVisibility(View.GONE);
        img5.setOnClickListener(this);

        img5hell = (ImageView) findViewById(R.id.Sonne5hell_imageView);
        img5hell.setOnClickListener(this);

        img6 = (ImageView) findViewById(R.id.Sonne6_imageView);
        img6.setVisibility(View.GONE);
        img6.setOnClickListener(this);

        img6hell = (ImageView) findViewById(R.id.Sonne6hell_imageView);
        img6hell.setOnClickListener(this);

        img7 = (ImageView) findViewById(R.id.Sonne7_imageView);
        img7.setVisibility(View.GONE);
        img7.setOnClickListener(this);

        img7hell = (ImageView) findViewById(R.id.Sonne7hell_imageView);
        img7hell.setOnClickListener(this);

        img8 = (ImageView) findViewById(R.id.Sonne8_imageView);
        img8.setVisibility(View.GONE);
        img8.setOnClickListener(this);

        img8hell = (ImageView) findViewById(R.id.Sonne8hell_imageView);
        img8hell.setOnClickListener(this);

        //Button and more action
        start = (Button) findViewById(R.id.startTour_Button);

        fertig = (Button) findViewById(R.id.fertig_Button);

        source = getIntent().getExtras().getInt("Source");

        if(source == 0){
            start.setVisibility(View.GONE);
            start.setOnClickListener(this);
            fertig.setVisibility(View.GONE);
            isfinished();
        }
        else{
            fertig.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
            count = 0;
            isfinished();
            fertig.setOnClickListener(this);
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
        menu.findItem(R.id.action_help).setVisible(true);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.action_help).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    //Menüaktivität
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.start_menu:
                startActivity(new Intent(this, LevelIntro.class));
                return true;

            case R.id.ziel:
                startActivity(new Intent(this, MenuZiel.class));
                return true;

            case R.id.tabelle:
                startActivity(new Intent(this, UebersichtTable.class));
                return true;

            case R.id.Sonne:
                startActivity(new Intent(this, SonneDerErkenntnisStart.class));
                return true;

            case R.id.Hausaufgabe:
                startActivity(new Intent(this, MenuHausaufgabe.class));
                return true;

            case R.id.Impressum:
                startActivity(new Intent(this, MenuImpressum.class));
                return true;

            case R.id.action_delete:
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().clear().commit();
                deleteFiles();
                startNew();
                return true;

            case R.id.action_help:
                AlertDialog.Builder builder = new AlertDialog.Builder(Level4SonneDerErkenntnis.this);
                builder.setTitle("Sonne der Erkenntnis - Hilfe");
                builder.setMessage("Klicke auf einen der Kreise um dir die dazugehörige Frage durchzulesen und deine Antwort aufzunehmen.\nWenn du schon eine Antwort aufgenommen hast, kannst du auf die erschienene Sonne klicken um deine Antwort erneut anzuhören oder zu verändern.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogX = builder.create();
                dialogX.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteFiles(){
        File file1 = new File(this.getFilesDir() +"/" + "sonne1" +".3gp");
        if(file1.exists()){
            file1.delete();
        }

        File file2 = new File(this.getFilesDir() + "/"+ "sonne2" + ".3gp");
        if(file2.exists()){
            file2.delete();
        }

        File file3 = new File(this.getFilesDir() + "/"+ "sonne3" + ".3gp");
        if(file3.exists()){
            file3.delete();
        }

        File file4 = new File(this.getFilesDir() + "/"+ "sonne4" + ".3gp");
        if(file4.exists()){
            file4.delete();
        }

        File file5 = new File(this.getFilesDir() + "/"+ "sonne5" + ".3gp");
        if(file5.exists()){
            file5.delete();
        }

        File file6 = new File(this.getFilesDir() + "/"+ "sonne6" + ".3gp");
        if(file6.exists()){
            file6.delete();
        }


        File file7 = new File(this.getFilesDir() + "/"+ "sonne7" + ".3gp");
        if(file7.exists()){
            file7.delete();
        }


        File file8 = new File(this.getFilesDir() + "/"+ "sonne8" + ".3gp");
        if(file8.exists()){
            file8.delete();
        }
    }

    public void isfinished(){
        File file1 = new File(this.getFilesDir() +"/" + "sonne1" +".3gp");
        Log.i(LOG_TAG, "Files List File: " + String.valueOf(file1.getAbsoluteFile()));
        if(file1.exists()){
            img1.setVisibility(View.VISIBLE);
            img1hell.setVisibility(View.GONE);

                fertig.setEnabled(true);
                fertig.setOnClickListener(this);

        }
        else if(file1.length() == 0){
            img1.setVisibility(View.GONE);
            img1hell.setVisibility(View.VISIBLE);

        }

        File file2 = new File(this.getFilesDir() + "/"+ "sonne2" + ".3gp");

        if(file2.exists()){
            img2.setVisibility(View.VISIBLE);
            img2hell.setVisibility(View.GONE);
            fertig.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
                fertig.setEnabled(true);
                fertig.setOnClickListener(this);

        }
        else if(file2.length() == 0){
            img2.setVisibility(View.GONE);
            img2hell.setVisibility(View.VISIBLE);
        }

        File file3 = new File(this.getFilesDir() + "/"+ "sonne3" + ".3gp");
        if(file3.exists()){
            img3.setVisibility(View.VISIBLE);
            img3hell.setVisibility(View.GONE);
            fertig.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
                fertig.setEnabled(true);
                fertig.setOnClickListener(this);

        }
        else if(file2.length() == 0){
            img3.setVisibility(View.GONE);
            img3hell.setVisibility(View.VISIBLE);
        }
        File file4 = new File(this.getFilesDir() + "/"+ "sonne4" + ".3gp");
        if(file4.exists()){
            img4.setVisibility(View.VISIBLE);
            img4hell.setVisibility(View.GONE);
            fertig.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
                fertig.setEnabled(true);
                fertig.setOnClickListener(this);

        }
        else if(file4.length() == 0){
            img4.setVisibility(View.GONE);
            img4hell.setVisibility(View.VISIBLE);
        }

        File file5 = new File(this.getFilesDir() + "/"+ "sonne5" + ".3gp");
        if(file5.exists()){
            img5.setVisibility(View.VISIBLE);
            img5hell.setVisibility(View.GONE);
            fertig.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
                fertig.setEnabled(true);
                fertig.setOnClickListener(this);

        }
        else if(file5.length() == 0){
            img5.setVisibility(View.GONE);
            img5hell.setVisibility(View.VISIBLE);
        }

        File file6 = new File(this.getFilesDir() + "/"+ "sonne6" + ".3gp");
        if(file6.exists()){
            img6.setVisibility(View.VISIBLE);
            img6hell.setVisibility(View.GONE);
            fertig.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
                fertig.setEnabled(true);
                fertig.setOnClickListener(this);

        }
        else if(file6.length() == 0){
            img6.setVisibility(View.GONE);
            img6hell.setVisibility(View.VISIBLE);
        }

        File file7 = new File(this.getFilesDir() + "/"+ "sonne7" + ".3gp");
        if(file7.exists()){
            img7.setVisibility(View.VISIBLE);
            img7hell.setVisibility(View.GONE);
            fertig.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
                fertig.setEnabled(true);
                fertig.setOnClickListener(this);

        }
        else if(file7.length() == 0){
            img7.setVisibility(View.GONE);
            img7hell.setVisibility(View.VISIBLE);
        }

        File file8 = new File(this.getFilesDir() + "/"+ "sonne8" + ".3gp");
        if(file8.exists()){
            img8.setVisibility(View.VISIBLE);
            img8hell.setVisibility(View.GONE);
            fertig.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
                fertig.setEnabled(true);
                fertig.setOnClickListener(this);

        }
        else if(file8.length() == 0){
            img8.setVisibility(View.GONE);
            img8hell.setVisibility(View.VISIBLE);
        }
    }

    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        saved = getSharedPreferences(PREFS_NAME,0);
        switch (view.getId()){

            case R.id.startTour_Button:
                intent = new Intent(view.getContext(), Sonne1.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            case R.id.Sonne1_imageView:
                intent = new Intent(view.getContext(), Sonne1.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.SonneHell1_imageView:
                intent = new Intent(view.getContext(), Sonne1.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne2_imageView:
                intent = new Intent(view.getContext(), Sonne2.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne2hell_imageView:
                intent = new Intent(view.getContext(), Sonne2.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne3_imageView:
                intent = new Intent(view.getContext(), Sonne3.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne3hell_imageView:
                intent = new Intent(view.getContext(), Sonne3.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne4_imageView:
                intent = new Intent(view.getContext(), Sonne4.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne4hell_imageView:
                intent = new Intent(view.getContext(), Sonne4.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne5_imageView:
                intent = new Intent(view.getContext(), Sonne5.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne5hell_imageView:
                intent = new Intent(view.getContext(), Sonne5.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne6_imageView:
                intent = new Intent(view.getContext(), Sonne6.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne6hell_imageView:
                intent = new Intent(view.getContext(), Sonne6.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne7_imageView:
                intent = new Intent(view.getContext(), Sonne7.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne7hell_imageView:
                intent = new Intent(view.getContext(), Sonne7.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;


            case R.id.Sonne8_imageView:
                intent = new Intent(view.getContext(), Sonne8.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;

            case R.id.Sonne8hell_imageView:
                intent = new Intent(view.getContext(), Sonne8.class);
                intent.putExtra("Tour", false);
                startActivity(intent);
                break;


            case R.id.fertig_Button:
                if (!saved.getBoolean("pause3", false)){
                    startActivity(new Intent(this, PauseZwischen4und5.class));
                }
                else{
                    startActivity(new Intent(this, Level5Start.class));
                }





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
