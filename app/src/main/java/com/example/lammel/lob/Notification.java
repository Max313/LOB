package com.example.lammel.lob;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.File;

public class Notification extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    // Button and more
    private Button weiter;

    //Days
    private Button m1;
    private Button mb1;
    private Boolean m1Set;
    private Button d1;
    private Button db1;
    private Boolean d1Set;
    private Button m2;
    private Button mb2;
    private Boolean m2Set;
    private Button d2;
    private Button db2;
    private Boolean d2Set;
    private Button f1;
    private Button fb1;
    private Boolean f1Set;
    private Button s1;
    private Button sb1;
    private Boolean s1Set;
    private Button s2;
    private Button sb2;
    private Boolean s2Set;

    //TimePicker
    private TimePicker tp;
    private int hours;
    private int minutes;

    //Notification Text
    private EditText txt;
    private String nTxt;

    private AppCompatDelegate delegate;

    //shared Preferences zum Speichern
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.setTitle("Erinnerung");

        //Set Status - Footer
        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();
        editor.putInt("tabStatus", 0);
        editor.apply();

        //Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.notification, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_notification);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);

        /**display Toolbar Icon
         delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
         delegate.getSupportActionBar().setLogo(R.drawable.kopficon);
         delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);*/

        weiter = (Button) findViewById(R.id.SpeichernN_Button);
        weiter.setOnClickListener(this);
        weiter.setEnabled(false);

        saved = getSharedPreferences(PREFS_NAME, 0);
        editor = saved.edit();


        //Monday
        m1 = (Button) findViewById(R.id.M1_Button);
        m1.setOnClickListener(this);

        mb1 = (Button) findViewById(R.id.M1b_Button);
        mb1.setOnClickListener(this);

        if(saved.getBoolean("Monday", false)){
            m1Set = true;
            m1.setVisibility(View.GONE);
            mb1.setVisibility(View.VISIBLE);
        }
        else{
            m1Set = false;
            m1.setVisibility(View.VISIBLE);
            mb1.setVisibility(View.GONE);
        }


        //Tuesday
        d1 = (Button) findViewById(R.id.D1_Button);
        d1.setOnClickListener(this);

        db1 = (Button) findViewById(R.id.D1b_Button);
        db1.setOnClickListener(this);

        if(saved.getBoolean("Tuesday", false)){
            d1Set = true;
            d1.setVisibility(View.GONE);
            db1.setVisibility(View.VISIBLE);
        }
        else{
            d1Set = false;
            d1.setVisibility(View.VISIBLE);
            db1.setVisibility(View.GONE);
        }

        //Wednesday
        m2 = (Button) findViewById(R.id.M2_Button);
        m2.setOnClickListener(this);

        mb2 = (Button) findViewById(R.id.M2b_Button);
        mb2.setOnClickListener(this);

        if(saved.getBoolean("Wednesday", false)){
            m2Set = true;
            m2.setVisibility(View.GONE);
            mb2.setVisibility(View.VISIBLE);
        }
        else{
            m2Set = false;
            m2.setVisibility(View.VISIBLE);
            mb2.setVisibility(View.GONE);
        }


        //Thursday
        d2 = (Button) findViewById(R.id.D2_Button);
        d2.setOnClickListener(this);

        db2 = (Button) findViewById(R.id.D2b_Button);
        db2.setOnClickListener(this);

        if(saved.getBoolean("Thursday", false)){
            d2Set = true;
            d2.setVisibility(View.GONE);
            db2.setVisibility(View.VISIBLE);
        }
        else{
            d2Set = false;
            d2.setVisibility(View.VISIBLE);
            db2.setVisibility(View.GONE);
        }


        //Friday
        f1 = (Button) findViewById(R.id.F1_Button);
        f1.setOnClickListener(this);

        fb1 = (Button) findViewById(R.id.F1b_Button);
        fb1.setOnClickListener(this);

        if(saved.getBoolean("Friday", false)){
            f1Set = true;
            f1.setVisibility(View.GONE);
            fb1.setVisibility(View.VISIBLE);
        }
        else{
            f1Set = false;
            f1.setVisibility(View.VISIBLE);
            fb1.setVisibility(View.GONE);
        }

        //Saturday
        s1 = (Button) findViewById(R.id.S1_Button);
        s1.setOnClickListener(this);

        sb1 = (Button) findViewById(R.id.S1b_Button);
        sb1.setOnClickListener(this);

        if(saved.getBoolean("Saturday", false)){
            s1Set = true;
            s1.setVisibility(View.GONE);
            sb1.setVisibility(View.VISIBLE);
        }
        else{
            s1Set = false;
            s1.setVisibility(View.VISIBLE);
            sb1.setVisibility(View.GONE);
        }

        //Sunday
        s2 = (Button) findViewById(R.id.S2_Button);
        s2.setOnClickListener(this);

        sb2 = (Button) findViewById(R.id.S2b_Button);
        sb2.setOnClickListener(this);

        if(saved.getBoolean("Sunday", false)){
            s2Set = true;
            s2.setVisibility(View.GONE);
            sb2.setVisibility(View.VISIBLE);
        }
        else{
            s2Set = false;
            s2.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.GONE);
        }
        //Handle EditText
        txt = (EditText) findViewById(R.id.notification_editText);
        txt.setHorizontallyScrolling(false);
        txt.setLines(6);
        txt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        txt.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (txt.length() == 0) {
                    weiter.setEnabled(false); //disable button if no text entered//
                } else {
                    nTxt = txt.getText().toString();
                    weiter.setEnabled(true);  //otherwise enabled
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        //TimePicker
        tp = (TimePicker) findViewById(R.id.timePicker);
        tp.setIs24HourView(true); // used to display AM/PM mode

        if(saved.getInt("Hours", 24) != 24){
            if (Build.VERSION.SDK_INT > 23){
            tp.setHour(saved.getInt("Hours", 0));
            }
            else {
                tp.setCurrentHour(saved.getInt("Hours", 0));
            }
        }

        if(saved.getInt("Minutes", 61) != 61){
            if (Build.VERSION.SDK_INT > 23){
            tp.setMinute(saved.getInt("Minutes", 61));
            }
            else{
                tp.setCurrentMinute(saved.getInt("Minutes", 0));
            }
        }
        // perform set on time changed listener event
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    hours = hourOfDay;
                    minutes = minute;

            }
        });
    }


    //Notifications for the different Days
    //Alarm each Monday
    private void setForMonday(){

    }

    //Alarm each Tuesday
    private void setForTuesday(){

    }

    //Alarm each Wednesday
    private void setForWednesday(){

    }

    //Alarm each Thursday
    private void setForThursday(){

    }

    //Alarm each Friday
    private void setForFriday(){

    }

    //Alarm each Saturday
    private void setForSaturday(){

    }

    //Alarm each Sunday
    private void setForSunday(){

    }




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
        if (!saved.getBoolean("MenuHausaufgabe", false)){
        menu.findItem(R.id.Hausaufgabe).setEnabled(false);
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
        startActivity(new Intent(this, Level4SonneDerErkenntnis.class));
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

    switch (v.getId()){

        case R.id.M1_Button:
            m1.setVisibility(View.GONE);
            mb1.setVisibility(View.VISIBLE);
            m1Set = true;
            break;

        case R.id.M1b_Button:
            m1.setVisibility(View.VISIBLE);
            mb1.setVisibility(View.GONE);
            m1Set = false;
            break;

        case R.id.D1_Button:
            d1.setVisibility(View.GONE);
            db1.setVisibility(View.VISIBLE);
            d1Set = true;
            break;

        case R.id.D1b_Button:
            d1.setVisibility(View.VISIBLE);
            db1.setVisibility(View.GONE);
            d1Set = false;
            break;

        case R.id.M2_Button:
            m2.setVisibility(View.GONE);
            mb2.setVisibility(View.VISIBLE);
            m2Set = true;
            break;

        case R.id.M2b_Button:
            m2.setVisibility(View.VISIBLE);
            mb2.setVisibility(View.GONE);
            m2Set = false;
            break;

        case R.id.D2_Button:
            d2.setVisibility(View.GONE);
            db2.setVisibility(View.VISIBLE);
            d2Set = true;
            break;

        case R.id.D2b_Button:
            d2.setVisibility(View.VISIBLE);
            db2.setVisibility(View.GONE);
            d2Set = false;
            break;

        case R.id.F1_Button:
            f1.setVisibility(View.GONE);
            fb1.setVisibility(View.VISIBLE);
            f1Set = true;
            break;

        case R.id.F1b_Button:
            f1.setVisibility(View.VISIBLE);
            fb1.setVisibility(View.GONE);
            f1Set = false;
            break;

        case R.id.S1_Button:
            s1.setVisibility(View.GONE);
            sb1.setVisibility(View.VISIBLE);
            s1Set = true;
            break;

        case R.id.S1b_Button:
            s1.setVisibility(View.VISIBLE);
            sb1.setVisibility(View.GONE);
            s1Set = false;
            break;

        case R.id.S2_Button:
            s2.setVisibility(View.GONE);
            sb2.setVisibility(View.VISIBLE);
            s2Set = true;
            break;

        case R.id.S2b_Button:
            s2.setVisibility(View.VISIBLE);
            sb2.setVisibility(View.GONE);
            s2Set = false;
            break;

        case R.id.SpeichernN_Button:
            if(m1Set){
                editor.putBoolean("Monday", true);
                setForMonday();
            }
            else{
                editor.putBoolean("Monday", false);
            }
            if(d1Set){
                editor.putBoolean("Tuesday", true);
                setForTuesday();
            }
            else{
                editor.putBoolean("Tuesday", false);

            }

            if(m2Set){
                editor.putBoolean("Thursday", true);
                setForThursday();
            }
            else{
                editor.putBoolean("Thursday", false);
            }

            if(d2Set){
                editor.putBoolean("Wednesday", true);
                setForWednesday();
            }
            else{
                editor.putBoolean("Wednesday", false);
            }

            if(f1Set){
                editor.putBoolean("Friday", true);
                setForFriday();
            }
            else{
                editor.putBoolean("Friday", false);
            }

            if(s1Set){
                editor.putBoolean("Saturday", true);
                setForSaturday();
            }
            else{
                editor.putBoolean("Saturday", false);
            }
            if(s2Set){
                editor.putBoolean("Sunday", true);
                setForSunday();
            }
            else{
                editor.putBoolean("Sunday", false);
            }

            editor.putInt("Hours", hours);
            editor.putInt("Minutes", minutes);

            startActivity(new Intent(this, Level1Start.class));
            break;

        default:
            break;
    }

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
