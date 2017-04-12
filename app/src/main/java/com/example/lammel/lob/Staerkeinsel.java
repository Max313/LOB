package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Staerkeinsel extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staerkeinsel);

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        switch (view.getId()) {
            case R.id.verhaltenTextView:
                builder.setTitle("Verhalten");
                builder.setMessage("Hier kommen die Tipps rein, wie dieses auszufüllen ist");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                break;

            case R.id.komplimentTextView:
                builder.setTitle("Kompliment");
                builder.setMessage("Hier kommen die Tipps rein, wie dieses auszufüllen ist");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK = builder.create();
                break;
            case R.id.ressourcenTextView:
                builder.setTitle("Ressourcen");
                builder.setMessage("Hier kommen die Tipps rein, wie dieses auszufüllen ist");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR = builder.create();
                break;

            default:
                break;
        }
    }
}
