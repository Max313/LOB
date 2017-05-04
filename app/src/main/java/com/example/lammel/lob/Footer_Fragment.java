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

public class Footer_Fragment extends Fragment implements View.OnClickListener{

    //stati
    public int zielStatus;
    public int ideeStatus;
    public int ressourceStatus;
    public int sonneStatus;
    public int loesungStatus;
    public int tabStatus;

    //Buttons
    private Button ziel;
    private ImageButton glowGrey;
    private ImageButton glowColor;
    private ImageButton glow;
    private Button ressource;
    private ImageButton sunGrey;
    private ImageButton sunColor;
    private ImageButton sun;
    private Button loesung;


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
        ziel = (Button) view.findViewById(R.id.ziel_Button);

        //Idee Button
        glowGrey = (ImageButton) view.findViewById(R.id.gluehbirneDurchsichtig_Button);
        glowColor = (ImageButton) view.findViewById(R.id.gluehbirneDunkel_Button);
        glow = (ImageButton) view.findViewById(R.id.gluehbirneLeuchtend_Button);

        //Ressource Button
        ressource = (Button) view.findViewById(R.id.ressourcen_Button);

        //Sun Button
        sunGrey = (ImageButton) view.findViewById(R.id.sonneGrau_Button);
        sunColor = (ImageButton) view.findViewById(R.id.sonneLeer_Button);
        sun = (ImageButton) view.findViewById(R.id.sonneLeuchtend_Button);

        //Loesung Button
        loesung = (Button) view.findViewById(R.id.loesung_Button);

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
                break;
            //in progress
            case 1:
                //Visibility and action
                ziel.setOnClickListener(this);
                break;
            //beendet
            case 2:

                break;

            default:
                break;
        }

        switch (ideeStatus){
            //noch nicht da
            case 0:
                //Visibility and action
                glowGrey.setVisibility(View.VISIBLE);
                glowColor.setVisibility(View.GONE);
                glow.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Visibility and action
                glowGrey.setVisibility(View.GONE);
                glowColor.setVisibility(View.VISIBLE);
                glow.setVisibility(View.GONE);
                glowColor.setOnClickListener(this);
                break;
            //beendet
            case 2:
                //Visibility and action
                glowGrey.setVisibility(View.GONE);
                glowColor.setVisibility(View.GONE);
                glow.setVisibility(View.VISIBLE);
                glow.setOnClickListener(this);
                break;

            default:
                break;
        }

        switch (ressourceStatus){
            //noch nicht da
            case 0:
                break;
            //in progress
            case 1:
                //Visibility and action
                ressource.setOnClickListener(this);
                break;
            //beendet
            case 2:

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
                break;
            //in progress
            case 1:
                //Visibility and action
                loesung.setOnClickListener(this);
                break;
            //beendet
            case 2:

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

            case R.id.ziel_Button:
                startActivity(new Intent(view.getContext(), Level1Problemdefinition.class));
                break;

            case R.id.gluehbirneLeuchtend_Button:
                startActivity(new Intent(view.getContext(), Level2Veraenderung.class));
                break;

            case R.id.ressourcen_Button:
                Intent intent = new Intent(view.getContext(), Staerkeinsel.class);
                intent.putExtra("LoesungsCounter", 0);
                startActivity(intent);
                break;

            case R.id.sonneLeuchtend_Button:
                startActivity(new Intent(view.getContext(), Level4InselDesSehenden.class));
                break;

            case R.id.loesung_Button:
                startActivity(new Intent(view.getContext(), Rueckblick.class));
                break;

            case R.id.gluehbirneDunkel_Button:
                startActivity(new Intent(view.getContext(), Level2Veraenderung.class));
                break;

            case R.id.sonneLeer_Button:
                startActivity(new Intent(view.getContext(), Level4InselDesSehenden.class));
                break;

            default:
                break;
        }

    }


}
