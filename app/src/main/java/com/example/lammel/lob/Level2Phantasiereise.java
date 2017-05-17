package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

public class Level2Phantasiereise extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons and more
    private Button phantasieWeiter;
    private ImageButton phantasiePlay, phantasiePause;
    private int counter = 0;
    private AppCompatDelegate delegate;
    private MediaPlayer player;
    private Boolean run = false;

    //shared Preferences Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    //Phantasiereise wird erzählt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_phantasiereise);
        this.setTitle("Lösungsweg 3");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.level2_phantasiereise, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_level2_phantasiereise);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level2));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.wegweiserb);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Buttons and more in action
        phantasieWeiter = (Button) findViewById(R.id.phantasie_ButtonWeiter);
        phantasieWeiter.setEnabled(false);
        phantasieWeiter.setOnClickListener(this);

        phantasiePlay = (ImageButton) findViewById(R.id.phantasie_play);
        phantasiePause = (ImageButton) findViewById(R.id.phantasie_pause);
        phantasiePlay.setVisibility(View.GONE);
        phantasiePlay.setOnClickListener(this);
        phantasiePause.setOnClickListener(this);


        player = MediaPlayer.create(Level2Phantasiereise.this,R.raw.phantasiereise);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                phantasieWeiter.setEnabled(true);
                phantasiePlay.setVisibility(View.VISIBLE);
                phantasiePause.setVisibility(View.GONE);
                run = false;
            }
        });

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
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        switch(item.getItemId()){
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

            case R.id.action_delete:
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                settings.edit().clear().commit();
                deleteFiles();
                startNew();
                return true;

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

    public void startNew(){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        final View view = v;
        AlertDialog.Builder builder = new AlertDialog.Builder(Level2Phantasiereise.this);
        switch (v.getId()){
            case R.id.phantasie_ButtonWeiter:
                player.pause();
                if(counter == 0){
                    editor.putBoolean("MünzeSave", true);
                    editor.apply();
                    builder.setTitle("Hausaufgabe");
                    builder.setMessage("Auch zu dieser Übung gibt es eine kleine Aufgabe, die du machen kannst um zu sehen, welche Auswirkungen diese neue Ansichtsweise haben kann.");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            startNext(view);
                        }
                    });
                    builder.setNeutralButton("Ansehen",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            startHausaufgaben();
                        }
                    });
                    AlertDialog dialogY = builder.create();
                    dialogY.show();
                    counter++;
                    break;
                }
                else if(counter == 1){
                    Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
                    intent.putExtra("LoesungsCounter", 3);
                    startActivity(intent);
                    break;
                }
                break;
            case R.id.phantasie_play:
                    onPlay(run);
                break;

            case R.id.phantasie_pause:
                    onPlay(run);
                break;
            default:
                break;
        }

    }

    private void onPlay(boolean start) {
        if (start) {
            pausePlaying();
        }

        else{
            startPlaying();
        }
    }

    private void startPlaying() {
        player.start();
        run = true;
        phantasiePlay.setVisibility(View.GONE);
        phantasiePause.setVisibility(View.VISIBLE);
    }

    private void pausePlaying() {
        player.pause();
        run = false;
        phantasiePlay.setVisibility(View.VISIBLE);
        phantasiePause.setVisibility(View.GONE);
    }

    private void startNext(View v) {
        Intent intent = new Intent(v.getContext(), Level2Loesungswege.class);
        intent.putExtra("LoesungsCounter", 3);
        startActivity(intent);
    }

    private void startHausaufgaben() {
        startActivity(new Intent(this, MenuHausaufgabe.class));
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
