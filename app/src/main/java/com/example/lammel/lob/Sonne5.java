package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.io.IOException;

public class Sonne5 extends FragmentActivity implements View.OnClickListener, AppCompatCallback {

    //Buttons and more
    private Button weiter;
    private Button uebersicht;
    private Boolean tour;
    private Intent intent;
    private ImageView record;
    private ImageView recordOn;
    private ImageView playEnabled;
    private ImageView play;
    private ImageView pause;
    private File file;
    private AppCompatDelegate delegate;
    private static final String MEDIA_NAME = "sonne5";

    //Tracker
    private Tracker mTracker;

    //Speicher
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;
    private SharedPreferences.Editor editor;

    //Speech Memo
    private static final String LOG_TAG = "AudioRecordTest";
    private MediaPlayer mPlayer;
    private MediaRecorder mRecorder;
    private boolean mStartRecording = true;
    private boolean mStartPlaying = true;
    private boolean pauseStart = false;
    private int milliSecond;

    //Progress Bar
    private ProgressBar progressBar;
    private TextView startValue;
    private TextView endValue;
    private int progressValue;
    private double start;
    private double end;
    private int minutes;
    private int seconds;
    private int sMinutes;
    private int sSeconds;
    private boolean run;
    private Handler handler;
    private int endTime;

    //Record Time
    private Handler rHandler;
    private TextView timeView;
    private Boolean startTimer;
    private int fullTime;
    private int rMinutes;
    private int rSeconds;
    private Button fertig;
    private Boolean rPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonne5);

        this.setTitle("Sonne der Erkenntnis 5/8");

        //Add Footer
        Footer_Fragment fragment = new Footer_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.sonne5_xml, fragment);
        transaction.commit();

        //Toolbar
        //Delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //Call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //Use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_sonne5);

        //Add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.level4));
        delegate.setSupportActionBar(toolbar);

        //display Toolbar Icon
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.drawable.sonnevoll);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Get tracker.
        ApplicationAnalytics application = (ApplicationAnalytics) getApplication();
        mTracker = application.getDefaultTracker();

        //Buttons and more on action
        tour = getIntent().getExtras().getBoolean("Tour");

        weiter = (Button) findViewById(R.id.Weiter5_Button);
        weiter.setOnClickListener(this);

        uebersicht = (Button) findViewById(R.id.zurUebersicht5_Button);
        uebersicht.setOnClickListener(this);

        playEnabled = (ImageView) findViewById(R.id.play5_Button);
        playEnabled.setOnClickListener(this);


        play = (ImageView) findViewById(R.id.playhell5_Button);


        pause = (ImageView) findViewById(R.id.pause5_Button);
        pause.setOnClickListener(this);

        record = (ImageView) findViewById(R.id.microphone5_Button1);
        record.setVisibility(View.VISIBLE);
        record.setOnClickListener(this);

        recordOn = (ImageView) findViewById(R.id.microphone5_Button2);
        recordOn.setOnClickListener(this);

        //Progress
        progressBar = (ProgressBar) findViewById(R.id.progressBar5);
        startValue = (TextView) findViewById(R.id.start5_TextView);
        endValue = (TextView) findViewById(R.id.end5_TextView);
        run = true;
        handler = new Handler();

        //RecordTimer
        timeView = (TextView) findViewById(R.id.timer5_TextView);
        rHandler = new Handler();
        startTimer = false;
        fertig = (Button) findViewById(R.id.fertig5_Button);
        fertig.setEnabled(false);
        fertig.setTextColor(Color.rgb(189,189,189));
        fertig.setOnClickListener(this);
        rPause = false;

        if(tour){
            uebersicht.setVisibility(View.GONE);
        }
        else{
            weiter.setVisibility(View.GONE);
        }

        file = new File(this.getFilesDir() +"/" + MEDIA_NAME +".3gp");
        Log.i(LOG_TAG, "FilesDir: "+String.valueOf(getFilesDir()));
        Log.i(LOG_TAG, "Files List File: " + String.valueOf(file.getAbsoluteFile()));

        if(!file.exists()) {
            file = new File(this.getFilesDir(), MEDIA_NAME +".3gp");
            play.setVisibility(View.VISIBLE);
            playEnabled.setVisibility(View.GONE);
            pause.setVisibility(View.GONE);

        }
        else if(file.length() > 0){
            Uri uri = Uri.parse(file.getAbsoluteFile().toString());
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(this,uri);
            String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            milliSecond = (Integer.parseInt(durationStr));
            initialProgressBar(milliSecond);
            Log.i(LOG_TAG, "File exist!");
            Log.i(LOG_TAG, "Files Length: "+ file.length());
        }
        else{
            playEnabled.setVisibility(View.GONE);
            play.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
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

    //Check if there is the needed permission to record_audio ////////////////////////////////////////
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case 200:
                if(!(grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    record.setEnabled(false);
                    play.setEnabled(false);
                    break;
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions,grantResults);
                break;
        }
    }

    //Recording and Play Audio /////////////////////////////////////////////////
    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            Log.i(LOG_TAG, "pauseStart = false");
            startPlaying();
        }

        else{
            pausePlaying();
            Log.i(LOG_TAG, "pauseStart = true");
        }
    }

    private void startPlaying() {
        if(end > 0 && end < endTime){
            run = true;
            mPlayer.start();
        }
        else {
            Log.i(LOG_TAG, "startPlaying");
            Uri uri = Uri.parse(file.getAbsoluteFile().toString());
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(this, uri);
            String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            milliSecond = (Integer.parseInt(durationStr));
            mPlayer = new MediaPlayer();
            try {
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.setDataSource(file.getAbsoluteFile().toString());
                mPlayer.prepare();

            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }
            run = true;
            handleProgress(milliSecond);
            mPlayer.start();

        }

    }

    private void pausePlaying(){
        run = false;
        mPlayer.pause();
    }

    private void stopPlaying() {
        playEnabled.setVisibility(View.VISIBLE);
        play.setVisibility(View.GONE);
        pause.setVisibility(View.GONE);
        progressBar.setProgress(0);
        minutes = endTime/60;
        seconds = (endTime % 60);
        startValue.setText("00:00");
        endValue.setText(String.format("%02d", minutes) +":" + String.format("%02d", seconds));
        run = false;
        pauseStart = true;
        mPlayer.release();
        mPlayer = null;
    }

    private void handleProgress(int mSecond){
        progressBar.setProgress(0);
        start = 0.00;
        endTime = mSecond/1000;
        minutes = endTime/60;
        seconds = (endTime % 60);
        end = endTime;
        Log.i(LOG_TAG, "Duration: " + endTime);
        // Start long running operation in a background thread

        new Thread(new Runnable() {
            public void run() {
                while (progressValue < endTime*10) {
                    if((progressValue % 10) == 0) {
                        Log.i(LOG_TAG, "ist == 0");
                        end -=1;
                        Log.i(LOG_TAG, String.valueOf(end));
                        minutes = (int) (end/60);
                        Log.i(LOG_TAG, "Minutes: " +String.valueOf(minutes));
                        seconds = (int) (end %60);
                        Log.i(LOG_TAG, "Seconds: " +String.valueOf(seconds));

                        start += 1;
                        sMinutes = (int) (start/60);
                        sSeconds = (int)(start % 60);

                    }
                    progressValue += 1;
                    // Update the progress bar and display the

                    // current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            if(run){
                                progressBar.setMax(endTime*10);
                                progressBar.setProgress(progressValue);
                                startValue.setText(String.format("%02d", sMinutes) + ":" + String.format("%02d", sSeconds));
                                endValue.setText("-" + String.format("%02d", minutes) + ":" +String.format("%02d", seconds));

                                if(progressValue == endTime*10){
                                    stopPlaying();
                                }
                            }
                        }
                    });
                    try {
                        // Sleep for 100 milliseconds.

                        // Just to display the progress slowly
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private void startRecording() {
        fertig.setEnabled(true);
        fertig.setTextColor(Color.rgb(255,255,255));
        resetProgressBar();
        Log.i(LOG_TAG, "Länge File: " + file.length());
        if(file.length() > 0){
            file.delete();
            file = new File(this.getFilesDir(), MEDIA_NAME + ".3gp");
        }
        Log.i(LOG_TAG, "Länge File: " + file.length());
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(file.getAbsoluteFile().toString());
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        startTimer = true;
        handleRecordTime();
        mRecorder.start();
    }

    private void pauseRecording(){
        if (Build.VERSION.SDK_INT < 24){
            rPause = false;
            stopRecording();
        }
        else{
            startTimer = false;
            mRecorder.pause();


        }
    }

    private void resumeRecording(){
        rPause = false;

        if (Build.VERSION.SDK_INT < 24){
            startTimer = false;
            mRecorder.stop();
            mRecorder.reset();
            mRecorder.release();
            mRecorder = null;
            startRecording();
        }
        else{
            startTimer = true;
            mRecorder.resume();

        }
    }

    private void stopRecording() {
        fertig.setEnabled(false);
        fertig.setTextColor(Color.rgb(189,189,189));
        startTimer = false;
        mRecorder.stop();
        Log.i(LOG_TAG, "Länge File: " + file.length());
        mRecorder.reset();
        mRecorder.release();
        Uri uri = Uri.parse(file.getAbsoluteFile().toString());
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(this,uri);
        String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        milliSecond = (Integer.parseInt(durationStr));
        initialProgressBar(milliSecond);
        resetRecordTime();
        mRecorder = null;

    }

    private void handleRecordTime(){
        fullTime = 0;
        rMinutes = 0;
        rSeconds = 0;

        // Start long running operation in a background thread

        new Thread(new Runnable() {
            public void run() {
                while (startTimer) {

                    fullTime+=1;
                    rMinutes = (int) (fullTime/60);
                    Log.i(LOG_TAG, "Minutes: " +String.valueOf(rMinutes));
                    rSeconds = (int) (fullTime %60);
                    Log.i(LOG_TAG, "Seconds: " +String.valueOf(rSeconds));


                    // Update current value in the text view
                    rHandler.post(new Runnable() {
                        public void run() {
                            timeView.setText(String.format("%02d", rMinutes) + ":" + String.format("%02d", rSeconds));
                        }
                    });
                    try {
                        // Sleep for 1000 milliseconds.

                        // Just to display the progress slowly
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void resetRecordTime(){
        timeView.setText("00:00");
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void initialProgressBar(int millisecond){
        if(millisecond > 0) {
            progressBar.setProgress(0);
            start = 0.00;
            endTime = millisecond / 1000;
            minutes = endTime/60;
            seconds = (endTime % 60);
            startValue.setText("00:00");
            endValue.setText(String.format("%02d", minutes) +":" + String.format("%02d", seconds));
            playEnabled.setVisibility(View.VISIBLE);
            play.setVisibility(View.GONE);
            pause.setVisibility(View.GONE);
        }
    }

    public void resetProgressBar(){
        progressBar.setProgress(0);
        startValue.setText("00:00");
        endValue.setText("00:00");
        playEnabled.setVisibility(View.GONE);
        play.setVisibility(View.VISIBLE);
        pause.setVisibility(View.GONE);

    }

    ///////////////////////////////////////////////////////////////////


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.Weiter5_Button:

                if(fertig.isEnabled()){
                    stopRecording();
                }
                intent = new Intent(view.getContext(), Sonne6.class);
                intent.putExtra("Tour", true);
                startActivity(intent);
                break;

            case R.id.zurUebersicht5_Button:

                if(fertig.isEnabled()){
                    stopRecording();
                }
                intent = new Intent(view.getContext(), Level4SonneDerErkenntnis.class);
                intent.putExtra("Source", 5);
                startActivity(intent);
                break;

            //Record - Play
            case R.id.microphone5_Button1:
                if(rPause){
                    resumeRecording();
                }
                else {
                    mStartRecording = true;
                    record.setVisibility(view.GONE);
                    recordOn.setVisibility(view.VISIBLE);
                    onRecord(mStartRecording);
                }
                break;

            case R.id.microphone5_Button2:
                rPause = true;
                recordOn.setVisibility(view.GONE);
                record.setVisibility(view.VISIBLE);
                pauseRecording();
                break;

            case R.id.fertig5_Button:
                rPause = false;
                mStartRecording = false;
                recordOn.setVisibility(view.GONE);
                record.setVisibility(view.VISIBLE);
                onRecord(mStartRecording);
                break;


            case R.id.play5_Button:
                Log.i(LOG_TAG,"playButton pressed");
                mStartPlaying = true;
                playEnabled.setVisibility(View.GONE);
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
                onPlay(mStartPlaying);
                break;

            case R.id.pause5_Button:
                pauseStart = true;
                run = false;
                Log.i(LOG_TAG,"pauseButton pressed");
                mStartPlaying = false;
                pause.setVisibility(View.GONE);
                playEnabled.setVisibility(View.VISIBLE);
                onPlay(mStartPlaying);
                break;


            default:
                break;
        }
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
