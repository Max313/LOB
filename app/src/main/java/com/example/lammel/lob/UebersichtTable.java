package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UebersichtTable extends AppCompatActivity implements View.OnClickListener {

    private Button weiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht_table);
        this.setTitle("LOB - Stärkeinsel - Übersicht");
        weiter = (Button) findViewById(R.id.weiterStaerkeButton);
        weiter.setOnClickListener(this);
        this.setTableContent();

    }

    private void setTableContent() {

        TextView text1View = (TextView) findViewById(R.id.Uebersicht1_1);
        text1View.setOnClickListener(this);

        if (Verhalten.FIRSTMESSAGE_VERHALTEN.length() <= 12) {
            text1View.setText(Verhalten.FIRSTMESSAGE_VERHALTEN);
        } else {
            String shortString = Verhalten.FIRSTMESSAGE_VERHALTEN.substring(0, 9) + "...";
            text1View.setText(shortString);
        }


        TextView text2View = (TextView) findViewById(R.id.Uebersicht2_1);
        text2View.setOnClickListener(this);

        if (Verhalten.SECONDMESSAGE_VERHALTEN.length() <= 12) {
            text2View.setText(Verhalten.SECONDMESSAGE_VERHALTEN);
        } else {
            String shortString = Verhalten.SECONDMESSAGE_VERHALTEN.substring(0, 9) + "...";
            text2View.setText(shortString);
        }


        TextView text3View = (TextView) findViewById(R.id.Uebersicht3_1);
        text3View.setOnClickListener(this);

        if (Verhalten.THIRDMESSAGE_VERHALTEN.length() <= 12) {
            text3View.setText(Verhalten.THIRDMESSAGE_VERHALTEN);
        } else {
            String shortString = Verhalten.THIRDMESSAGE_VERHALTEN.substring(0, 9) + "...";
            text3View.setText(shortString);
        }


        TextView text4View = (TextView) findViewById(R.id.Uebersicht1_2);
        text4View.setOnClickListener(this);

        if (Kompliment.FIRSTMESSAGE_KOMPLIMENT.length() <= 12) {
            text4View.setText(Kompliment.FIRSTMESSAGE_KOMPLIMENT);
        } else {
            String shortString = Kompliment.FIRSTMESSAGE_KOMPLIMENT.substring(0, 9) + "...";
            text4View.setText(shortString);
        }


        TextView text5View = (TextView) findViewById(R.id.Uebersicht2_2);
        text5View.setOnClickListener(this);

        if (Kompliment.SECONDMESSAGE_KOMPLIMENT.length() <= 12) {
            text5View.setText(Kompliment.SECONDMESSAGE_KOMPLIMENT);
        } else {
            String shortString = Kompliment.SECONDMESSAGE_KOMPLIMENT.substring(0, 9) + "...";
            text5View.setText(shortString);
        }


        TextView text6View = (TextView) findViewById(R.id.Uebersicht3_2);
        text6View.setOnClickListener(this);

        if (Kompliment.THIRDMESSAGE_KOMPLIMENT.length() <= 12) {
            text6View.setText(Kompliment.THIRDMESSAGE_KOMPLIMENT);
        } else {
            String shortString = Kompliment.THIRDMESSAGE_KOMPLIMENT.substring(0, 9) + "...";
            text6View.setText(shortString);
        }


        TextView text7View = (TextView) findViewById(R.id.Uebersicht1_3);
        text7View.setOnClickListener(this);

        if (Ressource.FIRSTMESSAGE_RESSOURCE.length() <= 12) {
            text7View.setText(Ressource.FIRSTMESSAGE_RESSOURCE);
        } else {
            String shortString = Ressource.FIRSTMESSAGE_RESSOURCE.substring(0, 9) + "...";
            text7View.setText(shortString);
        }

        TextView text8View = (TextView) findViewById(R.id.Uebersicht2_3);
        text8View.setOnClickListener(this);

        if (Ressource.SECONDMESSAGE_RESSOURCE.length() <= 12) {
            text8View.setText(Ressource.SECONDMESSAGE_RESSOURCE);
        } else {
            String shortString = Ressource.SECONDMESSAGE_RESSOURCE.substring(0, 9) + "...";
            text8View.setText(shortString);
        }


        TextView text9View = (TextView) findViewById(R.id.Uebersicht3_3);
        text9View.setOnClickListener(this);

        if (Ressource.THIRDMESSAGE_RESSOURCE.length() <= 12) {
            text9View.setText(Ressource.THIRDMESSAGE_RESSOURCE);
        } else {
            String shortString = Ressource.THIRDMESSAGE_RESSOURCE.substring(0, 9) + "...";
            text8View.setText(shortString);
        }

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UebersichtTable.this);
        switch (view.getId()) {
            case R.id.Uebersicht1_1:

                builder.setTitle("Verhalten");
                builder.setMessage(Verhalten.FIRSTMESSAGE_VERHALTEN);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV1 = builder.create();
                dialogV1.show();
                break;

            case R.id.Uebersicht1_2:

                builder.setTitle("Verhalten");
                builder.setMessage(Verhalten.SECONDMESSAGE_VERHALTEN);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV2 = builder.create();
                dialogV2.show();
                break;

            case R.id.Uebersicht1_3:

                builder.setTitle("Verhalten");
                builder.setMessage(Verhalten.THIRDMESSAGE_VERHALTEN);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV3 = builder.create();
                dialogV3.show();
                break;

            case R.id.Uebersicht2_1:

                builder.setTitle("Kompliment");
                builder.setMessage(Kompliment.FIRSTMESSAGE_KOMPLIMENT);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK1 = builder.create();
                dialogK1.show();
                break;

            case R.id.Uebersicht2_2:

                builder.setTitle("Kompliment");
                builder.setMessage(Kompliment.SECONDMESSAGE_KOMPLIMENT);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK2 = builder.create();
                dialogK2.show();
                break;

            case R.id.Uebersicht2_3:

                builder.setTitle("Kompliment");
                builder.setMessage(Kompliment.THIRDMESSAGE_KOMPLIMENT);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK3 = builder.create();
                dialogK3.show();
                break;

            case R.id.Uebersicht3_1:

                builder.setTitle("Ressourcen");
                builder.setMessage(Ressource.FIRSTMESSAGE_RESSOURCE);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR1 = builder.create();
                dialogR1.show();
                break;

            case R.id.Uebersicht3_2:

                builder.setTitle("Ressourcen");
                builder.setMessage(Ressource.SECONDMESSAGE_RESSOURCE);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR2 = builder.create();
                dialogR2.show();
                break;

            case R.id.Uebersicht3_3:

                builder.setTitle("Ressourcen");
                builder.setMessage(Ressource.THIRDMESSAGE_RESSOURCE);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR3 = builder.create();
                dialogR3.show();
                break;

            case R.id.weiterStaerkeButton:
                startActivity(new Intent(this, Level3UebungStart.class));

            default:
                break;
        }
    }
}
