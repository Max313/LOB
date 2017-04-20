package com.example.lammel.lob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class Staerkeinsel extends AppCompatActivity implements View.OnClickListener{

    private TextView verhalten;
    private TextView kompliment;
    private TextView ressourcen;
    private Button speichern;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staerkeinsel);
        this.setTitle("LOB - Stärkeinsel - Intro");
        verhalten = (TextView) findViewById(R.id.verhaltenTextView);
        verhalten.setOnClickListener(this);

        kompliment = (TextView) findViewById(R.id.komplimentTextView);
        kompliment.setOnClickListener(this);

        ressourcen = (TextView) findViewById(R.id.ressourcenTextView);
        ressourcen.setOnClickListener(this);

        speichern = (Button) findViewById(R.id.weiterStaerkeButton);
        speichern.setOnClickListener(this);

        table = (TableLayout) findViewById(R.id.tableSmall);
        table.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Staerkeinsel.this);
        switch (view.getId()) {
            case R.id.verhaltenTextView:

                builder.setTitle("Verhalten");
                builder.setMessage("Welches Verhalten an dir fällt dir positiv auf und wie würdest du es charakterisieren. \nz.B. Selbst in einer schwierigen Situation versuche ich das beste für mich und die betroffenen Personen zu machen.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogV = builder.create();
                dialogV.show();
                break;

            case R.id.komplimentTextView:
                builder.setTitle("Kompliment");
                builder.setMessage("Gebe dir selbst ein Kompliment, so wie du es auch einem guten Freund geben würdest. \nz.B. Ich gehe umsichtig und überlegt an die Situation heran.");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogK = builder.create();
                dialogK.show();
                break;
            case R.id.ressourcenTextView:
                builder.setTitle("Ressourcen");
                builder.setMessage("Ressourcen die du zur Lösung einer schwierigen Situation beitragen. \nz.B. Umsicht");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialogR = builder.create();
                dialogR.show();
                break;

            case R.id.weiterStaerkeButton:
                startActivity(new Intent(this, Verhalten.class));


            case R.id.tableSmall:
                startActivity(new Intent(this, Verhalten.class));
            default:
                break;
        }
    }
}
