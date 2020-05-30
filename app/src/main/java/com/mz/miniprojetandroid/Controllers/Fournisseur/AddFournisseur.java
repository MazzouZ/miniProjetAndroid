package com.mz.miniprojetandroid.Controllers.Fournisseur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.Models.Fournisseur;
import com.mz.miniprojetandroid.R;

public class AddFournisseur extends AppCompatActivity {

    private EditText fournisseurNom;
    private EditText fournisseurEmail;
    private EditText fournisseurAdresse;
    private EditText fournisseurTelephone;
    private Button   mAddBtn;

    private miniProjetDBHelper dbHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fournisseur);

        fournisseurNom = (EditText) findViewById(R.id.nomAdd);
        fournisseurEmail = (EditText) findViewById(R.id.emailAdd);
        fournisseurAdresse = (EditText) findViewById(R.id.adresseAdd);
        fournisseurTelephone = (EditText) findViewById(R.id.telephoneAdd);
        mAddBtn = (Button) findViewById(R.id.Ajouter);
        
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFournisseur();
            }
        });

    }

    private void saveFournisseur() {
        String nom = fournisseurNom.getText().toString().trim();
        String adresse = fournisseurEmail.getText().toString().trim();
        String email = fournisseurAdresse.getText().toString().trim();
        String telephone = fournisseurTelephone.getText().toString().trim();
        dbHelper = new miniProjetDBHelper(AddFournisseur.this);

        if (nom.isEmpty()) {
            Toast.makeText(this, "Le nom est obligatoire", Toast.LENGTH_SHORT).show();
        }

        if (adresse.isEmpty()) {
            Toast.makeText(this, "L'adresse est obligatoire", Toast.LENGTH_SHORT).show();
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "L'email est obligatoire", Toast.LENGTH_SHORT).show();
        }

        if (telephone.isEmpty()) {
            Toast.makeText(this, "Le Telephone est obligatoire", Toast.LENGTH_SHORT).show();
        }

        Fournisseur fournisseur = new Fournisseur(nom, adresse, email, telephone);
        dbHelper.saveNewFournisseur(fournisseur);
        goBackHome();

    }

    private void goBackHome() {
        startActivity(new Intent(AddFournisseur.this, Fournisseurs.class));
    }
}