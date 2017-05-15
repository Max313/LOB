package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

public class ZehnTage extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    private TextView timer;
    private Button fertig;
    private CountDownTimer countdown;
    private EditText edit1, edit2, edit3, edit4, edit5;
    private String inhalt1, inhalt2, inhalt3, inhalt4, inhalt5;

    //Toolbar
    private AppCompatDelegate delegate;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    //Während den 10 Tagen Wartezeit kann man Liste zusammenstellen von Dingen auf die man besonders stolz ist
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zehn_tage);

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.zehnTage, fragment);
        transaction.commit();

        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_zehn_tage);

        //Add the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.wegweiserbuntshort);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        fertig = (Button) findViewById(R.id.zehnTage_Button);
        fertig.setEnabled(false);
        fertig.setOnClickListener(this);

        //Speicherung Text
        saved = getSharedPreferences(PREFS_NAME, 0);

        inhalt1 = saved.getString("ZehntageEdit1", "");
        inhalt2 = saved.getString("ZehntageEdit2", "");
        inhalt3 = saved.getString("ZehntageEdit3", "");
        inhalt4 = saved.getString("ZehntageEdit4", "");
        inhalt5 = saved.getString("ZehntageEdit5", "");

        edit1 = (EditText) findViewById(R.id.zehnTage_edittext1);
        edit2 = (EditText) findViewById(R.id.zehnTage_edittext2);
        edit3 = (EditText) findViewById(R.id.zehnTage_edittext3);
        edit4 = (EditText) findViewById(R.id.zehnTage_edittext4);
        edit5 = (EditText) findViewById(R.id.zehnTage_edittext5);

        if(inhalt1 != ""){
            edit1.setText(inhalt1);
            fertig.setEnabled(true);
        }
        if(inhalt2 != ""){
            edit2.setText(inhalt2);
            fertig.setEnabled(true);
        }
        if(inhalt3 != ""){
            edit3.setText(inhalt3);
            fertig.setEnabled(true);
        }
        if(inhalt4 != ""){
            edit4.setText(inhalt4);
            fertig.setEnabled(true);
        }
        if(inhalt5 != ""){
            edit5.setText(inhalt5);
            fertig.setEnabled(true);
        }

        //Timer der 10 Tage runterläuft 864000000 ms
        //Timer der 1 Min runterläuft 60000 ms
                countdown = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer = (TextView) findViewById(R.id.zehnTage_Textview2);
                timer.setText("Tage verbleibend: " + (millisUntilFinished));// / 86400000+1));
                long rest = millisUntilFinished; /// 86400000+1;
                editor = saved.edit();
                editor.putLong("TimeZehntage", rest);
                editor.apply();

            }

            public void onFinish() {
                timer.setText("Geschafft!");
                fertig.setEnabled(true);
            }
        }.start();



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
    public void onClick(View view) {
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        inhalt1 = edit1.getText().toString();
        editor.putString("ZehntageEdit1", inhalt1);
        inhalt2 = edit2.getText().toString();
        editor.putString("ZehntageEdit2", inhalt2);
        inhalt3 = edit3.getText().toString();
        editor.putString("ZehntageEdit3", inhalt3);
        inhalt4 = edit4.getText().toString();
        editor.putString("ZehntageEdit4", inhalt4);
        inhalt5 = edit5.getText().toString();
        editor.putString("ZehntageEdit5", inhalt5);

        editor.apply();

        Intent intent = new Intent(view.getContext(), Level2Loesungswege.class);
        intent.putExtra("LoesungsCounter", 5);
        startActivity(intent);
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
