package com.example.lammel.lob;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        this.setTitle("LOB - Willkommen");

        //Check for Permission preparation
        int userVersion = Build.VERSION.SDK_INT;
        if(userVersion > Build.VERSION_CODES.LOLLIPOP_MR1){
            if(!checkIfAlreadyHavePermission()){
                requestForSpecificPermission();
            }
        }

        //Action preparation
        Button start_button = (Button) findViewById(R.id.start_button);
        start_button.setOnClickListener(this);
    }

    //Menu: disable Items if necessary
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.ziel).setEnabled(false);
        menu.findItem(R.id.tabelle).setEnabled(false);
        menu.findItem(R.id.Sonne).setEnabled(false);
        return true;
    }

    //Menu setup
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //handle selection of items
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Level1Onboarding.class));
    }



    //Check for permission
    private boolean checkIfAlreadyHavePermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }

    }

    //Ask for Permission
    private void requestForSpecificPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 101);
    }

}
