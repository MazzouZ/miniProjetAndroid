package com.mz.miniprojetandroid.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mz.miniprojetandroid.Controllers.Fournisseur.Fournisseurs;
import com.mz.miniprojetandroid.Controllers.Medicament.Medicaments;
import com.mz.miniprojetandroid.R;

public class Navigateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigateur);

        Button four,med;
        four=findViewById(R.id.four);
        med=findViewById(R.id.med);

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Fournisseurs.class);
                startActivity(intent);
            }
        });

        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Medicaments.class);
                startActivity(intent);
            }
        });
    }
}
