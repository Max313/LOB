package com.example.lammel.lob;

import android.content.Intent;
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
    public int forwardStatus;

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
    private ImageButton forward;
    private ImageButton forwardDisabled;

    //TextView
    private TextView eins;
    private TextView zwei;
    private TextView drei;
    private TextView vier;
    private TextView fuenf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.footer, container, false);

        //Button forward
        forward = (ImageButton) view.findViewById(R.id.forward_Button);
        forwardDisabled = (ImageButton) view.findViewById(R.id.forwardgrey_Button);

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
        zielStatus = MainActivity.zielStatus;
        ideeStatus = MainActivity.ideeStatus;
        ressourceStatus = MainActivity.ressourceStatus;
        sonneStatus = MainActivity.sonneStatus;
        loesungStatus = MainActivity.loesungStatus;

        setFooterContent();
    }

    public void setFooterContent(){
        switch(forwardStatus){
            //Enabled
            case 0:
                forward.setVisibility(View.VISIBLE);
                forwardDisabled.setVisibility(View.GONE);
                break;
            //Disabled
            case 1:
                forward.setVisibility(View.GONE);
                forwardDisabled.setVisibility(View.VISIBLE);
                break;
        }
        switch(zielStatus){
            //noch nicht da
            case 0:
                //Tab
                eins.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Tab
                eins.setVisibility(View.VISIBLE);
                break;
            //beendet
            case 2:
                //Icon
                ziel.setOnClickListener(this);
                //Tab
                eins.setVisibility(View.GONE);
                break;

            default:
                break;
        }

        switch (ideeStatus){
            //noch nicht da
            case 0:
                //Icon
                glowGrey.setVisibility(View.VISIBLE);
                glowColor.setVisibility(View.GONE);
                glow.setVisibility(View.GONE);
                //Tab
                zwei.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Icon
                glowGrey.setVisibility(View.GONE);
                glowColor.setVisibility(View.VISIBLE);
                glow.setVisibility(View.GONE);
                //Tab
                zwei.setVisibility(View.VISIBLE);
                break;
            //beendet
            case 2:
                //Icon
                glowGrey.setVisibility(View.GONE);
                glowColor.setVisibility(View.GONE);
                glow.setVisibility(View.VISIBLE);
                glow.setOnClickListener(this);
                //Tab
                zwei.setVisibility(View.GONE);
                break;

            default:
                break;
        }

        switch (ressourceStatus){
            //noch nicht da
            case 0:
                //Tab
                drei.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Tab
                drei.setVisibility(View.VISIBLE);
                break;
            //beendet
            case 2:
                //Icon
                ressource.setOnClickListener(this);
                //Tab
                drei.setVisibility(View.GONE);
                break;

            default:
                break;
        }

        switch (sonneStatus){
            //noch nicht da
            case 0:
                //Icon
                sunGrey.setVisibility(View.VISIBLE);
                sunColor.setVisibility(View.GONE);
                sun.setVisibility(View.GONE);
                //Tab
                vier.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Icon
                sunGrey.setVisibility(View.GONE);
                sunColor.setVisibility(View.VISIBLE);
                sun.setVisibility(View.GONE);
                //Tab
                vier.setVisibility(View.VISIBLE);
                break;
            //beendet
            case 2:
                //Icon
                sunGrey.setVisibility(View.GONE);
                sunColor.setVisibility(View.GONE);
                sun.setVisibility(View.VISIBLE);
                sun.setOnClickListener(this);
                //Tab
                vier.setVisibility(View.GONE);
                break;

            default:
                break;
        }
        switch (loesungStatus){
            //noch nicht da
            case 0:
                //Tab
                fuenf.setVisibility(View.GONE);
                break;
            //in progress
            case 1:
                //Tab
                fuenf.setVisibility(View.VISIBLE);
                break;
            //beendet
            case 2:
                //Icon
                loesung.setOnClickListener(this);
                //Tab
                fuenf.setVisibility(View.GONE);
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

            default:
                break;
        }

    }


}
