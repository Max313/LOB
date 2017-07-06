package com.example.lammel.lob;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.lammel.lob.R.id.container;

/**
 * Diese Klasse beschäftigt sich mit dem Footer und der Veränderung Eigenschaften sowie der Bilder
 * Zu Beginn und zu Ende eines Levels ändert sich die Funktionalität
 * Ein Level kann noch nicht freigschaltet (grau), das aktuelle Level sein (halb bunt) oder freisgeschalten (bunt) sein
 * außerdem wird angezeigt wo man sich gerade befindet
 */

public class Footer_Fragment extends Fragment implements View.OnClickListener{

    //stati
    public int zielStatus;
    public int ideeStatus;
    public int ressourceStatus;
    public int sonneStatus;
    public int loesungStatus;
    public int tabStatus;

    //Buttons
    private ImageButton zielsw;
    private ImageButton zielb;
    private ImageButton ziel;
    private ImageButton wegweisersw;
    private ImageButton wegweiserb;
    private ImageButton wegweiser;
    private ImageButton ressourcesw;
    private ImageButton ressourceb;
    private ImageButton ressource;
    private ImageButton sunGrey;
    private ImageButton sunColor;
    private ImageButton sun;
    private ImageButton loesungsw;
    private ImageButton loesungb;
    private ImageButton loesung;


    //TextView
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;

    //shared Preferences
    public static final String PREFS_NAME = "LOBPrefFile";
    private SharedPreferences saved;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.footer, container, false);

        //Ziel Button
        zielsw = (ImageButton) view.findViewById(R.id.ziel1_Button);
        zielb = (ImageButton) view.findViewById(R.id.ziel2_Button);
        ziel = (ImageButton) view.findViewById(R.id.ziel3_Button);

        //Idee Button
         wegweisersw= (ImageButton) view.findViewById(R.id.wegweiser1_Button);
        wegweiserb = (ImageButton) view.findViewById(R.id.wegweiser2_Button);
        wegweiser = (ImageButton) view.findViewById(R.id.wegweiser3_Button);

        //Ressource Button
        ressourcesw = (ImageButton) view.findViewById(R.id.ressourcen1_Button);
        ressourceb = (ImageButton) view.findViewById(R.id.ressourcen2_Button);
        ressource = (ImageButton) view.findViewById(R.id.ressourcen3_Button);

        //Sun Button
        sunGrey = (ImageButton) view.findViewById(R.id.sonneGrau_Button);
        sunColor = (ImageButton) view.findViewById(R.id.sonneLeer_Button);
        sun = (ImageButton) view.findViewById(R.id.sonneLeuchtend_Button);

        //Loesung Button
        loesungsw = (ImageButton) view.findViewById(R.id.loesung1_Button);
        loesungb = (ImageButton) view.findViewById(R.id.loesung2_Button);
        loesung = (ImageButton) view.findViewById(R.id.loesung3_Button);

        //Tabs
        eins = (TextView) view.findViewById(R.id.footer1_TextView);
        zwei = (TextView) view.findViewById(R.id.footer2_TextView);
        drei = (TextView) view.findViewById(R.id.footer3_TextView);
        vier = (TextView) view.findViewById(R.id.footer4_TextView);
        fuenf = (TextView) view.findViewById(R.id.footer5_TextView);

        updateStati();

        return view;

    }

    public void updateStati(){
        saved = this.getActivity().getSharedPreferences(PREFS_NAME, 0);

        zielStatus =  saved.getInt("zielStatus", 0);
        ideeStatus = saved.getInt("ideeStatus", 0);
        ressourceStatus = saved.getInt("ressourceStatus", 0);
        sonneStatus = saved.getInt("sonneStatus", 0);
        loesungStatus = saved.getInt("loesungStatus", 0);

        tabStatus = saved.getInt("tabStatus", 0);

        setPictures();
        setTab();
    }

    public void setPictures(){

        switch(zielStatus){
            //noch nicht da
            case 0:
                //Visibility and action
                zielsw.setVisibility(View.VISIBLE);
                zielb.setVisibility(View.GONE);
                ziel.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Visibility and action
                zielsw.setVisibility(View.GONE);
                zielb.setVisibility(View.VISIBLE);
                ziel.setVisibility(View.GONE);
                zielb.setOnClickListener(this);
                break;
            //beendet
            case 2:
                //Visibility and action
                zielsw.setVisibility(View.GONE);
                zielb.setVisibility(View.GONE);
                ziel.setVisibility(View.VISIBLE);
                ziel.setOnClickListener(this);
                break;

            default:
                break;
        }

        switch (ideeStatus){
            //noch nicht da
            case 0:
                //Visibility and action
                wegweisersw.setVisibility(View.VISIBLE);
                wegweiserb.setVisibility(View.GONE);
                wegweiser.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Visibility and action
                wegweisersw.setVisibility(View.GONE);
                wegweiserb.setVisibility(View.VISIBLE);
                wegweiser.setVisibility(View.GONE);
                wegweiserb.setOnClickListener(this);
                break;
            //beendet
            case 2:
                //Visibility and action
                wegweisersw.setVisibility(View.GONE);
                wegweiserb.setVisibility(View.GONE);
                wegweiser.setVisibility(View.VISIBLE);
                wegweiser.setOnClickListener(this);
                break;

            default:
                break;
        }

        switch (ressourceStatus){
            //noch nicht da
            case 0:
                //Visibility and action
                ressourcesw.setVisibility(View.VISIBLE);
                ressourceb.setVisibility(View.GONE);
                ressource.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Visibility and action
                ressourcesw.setVisibility(View.GONE);
                ressourceb.setVisibility(View.VISIBLE);
                ressource.setVisibility(View.GONE);
                ressourceb.setOnClickListener(this);
                break;
            //beendet
            case 2:
                //Visibility and action
                ressourcesw.setVisibility(View.GONE);
                ressourceb.setVisibility(View.GONE);
                ressource.setVisibility(View.VISIBLE);
                ressource.setOnClickListener(this);
                break;

            default:
                break;
        }

        switch (sonneStatus){
            //noch nicht da
            case 0:
                //Visibility and action
                sunGrey.setVisibility(View.VISIBLE);
                sunColor.setVisibility(View.GONE);
                sun.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Visibility and action
                sunGrey.setVisibility(View.GONE);
                sunColor.setVisibility(View.VISIBLE);
                sun.setVisibility(View.GONE);
                sunColor.setOnClickListener(this);
                break;
            //beendet
            case 2:
                //Visibility and action
                sunGrey.setVisibility(View.GONE);
                sunColor.setVisibility(View.GONE);
                sun.setVisibility(View.VISIBLE);
                sun.setOnClickListener(this);
                break;

            default:
                break;
        }
        switch (loesungStatus){
            //noch nicht da
            case 0:
                //Visibility and action
                loesungsw.setVisibility(View.VISIBLE);
                loesungb.setVisibility(View.GONE);
                loesung.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Visibility and action
                loesungsw.setVisibility(View.GONE);
                loesungb.setVisibility(View.VISIBLE);
                loesung.setVisibility(View.GONE);
                loesungb.setOnClickListener(this);
                break;
            //beendet
            case 2:
                //Visibility and action
                loesungsw.setVisibility(View.GONE);
                loesungb.setVisibility(View.GONE);
                loesung.setVisibility(View.VISIBLE);
                loesung.setOnClickListener(this);
                break;

            default:
                break;
        }
    }

    public void setTab(){
        switch(tabStatus){
            case 0:
                eins.setVisibility(View.GONE);
                zwei.setVisibility(View.GONE);
                drei.setVisibility(View.GONE);
                vier.setVisibility(View.GONE);
                fuenf.setVisibility(View.GONE);
                break;

            case 1:
                eins.setVisibility(View.VISIBLE);
                zwei.setVisibility(View.GONE);
                drei.setVisibility(View.GONE);
                vier.setVisibility(View.GONE);
                fuenf.setVisibility(View.GONE);
                break;

            case 2:
                eins.setVisibility(View.GONE);
                zwei.setVisibility(View.VISIBLE);
                drei.setVisibility(View.GONE);
                vier.setVisibility(View.GONE);
                fuenf.setVisibility(View.GONE);
                break;

            case 3:
                eins.setVisibility(View.GONE);
                zwei.setVisibility(View.GONE);
                drei.setVisibility(View.VISIBLE);
                vier.setVisibility(View.GONE);
                fuenf.setVisibility(View.GONE);
                break;

            case 4:
                eins.setVisibility(View.GONE);
                zwei.setVisibility(View.GONE);
                drei.setVisibility(View.GONE);
                vier.setVisibility(View.VISIBLE);
                fuenf.setVisibility(View.GONE);
                break;

            case 5:
                eins.setVisibility(View.GONE);
                zwei.setVisibility(View.GONE);
                drei.setVisibility(View.GONE);
                vier.setVisibility(View.GONE);
                fuenf.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ziel2_Button:
                startActivity(new Intent(view.getContext(), Level1Start.class));
                break;

            case R.id.ziel3_Button:
                startActivity(new Intent(view.getContext(), Level1Start.class));
                break;

            case R.id.wegweiser2_Button:
                startActivity(new Intent(view.getContext(), Level2Start.class));
                break;

            case R.id.wegweiser3_Button:
                startActivity(new Intent(view.getContext(), Level2Start.class));
                break;

            case R.id.ressourcen2_Button:
                Intent intent = new Intent(view.getContext(), Level3Start.class);
                intent.putExtra("LoesungsCounter", 0);
                startActivity(intent);
                break;

            case R.id.ressourcen3_Button:
                Intent intent2 = new Intent(view.getContext(), Level3Start.class);
                intent2.putExtra("LoesungsCounter", 0);
                startActivity(intent2);
                break;

            case R.id.sonneLeer_Button:
                startActivity(new Intent(view.getContext(), Level4Start.class));
                break;

            case R.id.sonneLeuchtend_Button:
                startActivity(new Intent(view.getContext(), Level4Start.class));
                break;

            case R.id.loesung2_Button:
                startActivity(new Intent(view.getContext(), Level5Start.class));
                break;

            case R.id.loesung3_Button:
                startActivity(new Intent(view.getContext(), Level5Start.class));
                break;

            default:
                break;
        }

    }




}
