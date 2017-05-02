package com.example.lammel.lob;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lammel on 28.04.17.
 */


public class Beenden extends AppCompatActivity {

    private static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences settings;
    SharedPreferences.Editor editor;
    private String ziel;

    @Override
    protected void onStop() {
        super.onStop();
        ziel = Level1Zieldefinition.getZiel();

        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        editor.putString("ZielString", ziel);

        // Commit the edits!
        editor.commit();
    }
}

