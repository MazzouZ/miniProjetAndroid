package com.mz.miniprojetandroid.Controllers.Fournisseur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.Models.Fournisseur;
import com.mz.miniprojetandroid.R;

public class UpdateFournisseur extends AppCompatActivity {

    private EditText fournisseurNom;
    private EditText fournisseurEmail;
    private EditText fournisseurAdresse;
    private EditText fournisseurTelephone;
    private Button mUpdateBtn;
    private miniProjetDBHelper dbHelper;
    private long receivedFournisseurId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fournisseur);
        fournisseurNom = (EditText) findViewById(R.id.nomUpdate);
        fournisseurEmail = (EditText) findViewById(R.id.emailUpdate);
        fournisseurAdresse = (EditText) findViewById(R.id.adresseUpdate);
        fournisseurTelephone = (EditText) findViewById(R.id.telephoneUpdate);
        mUpdateBtn = (Button) findViewById(R.id.modifier);
        dbHelper = new miniProjetDBHelper(UpdateFournisseur.this);

        try {
            receivedFournisseurId = getIntent().getLongExtra("FournisseurId", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Fournisseur queriedFournisseur = dbHelper.getFournisseur(receivedFournisseurId);
        fournisseurNom.setText(queriedFournisseur.getNom());
        fournisseurEmail.setText(queriedFournisseur.getEmail());
        fournisseurAdresse.setText(queriedFournisseur.getAdresse());
        fournisseurTelephone.setText(queriedFournisseur.getTelephone());

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateFournisseur();
            }
        });
    }

    private void updateFournisseur() {
        String nom = fournisseurNom.getText().toString().trim();
        String adresse = fournisseurEmail.getText().toString().trim();
        String email = fournisseurAdresse.getText().toString().trim();
        String telephone = fournisseurTelephone.getText().toString().trim();

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
        Fournisseur updatedFournisseur = new Fournisseur(nom, adresse, email, telephone);

        dbHelper.updateFournisseurRecord(receivedFournisseurId, UpdateFournisseur.this, updatedFournisseur);
        goBackHome();

    }

    private void goBackHome() {
        startActivity(new Intent(UpdateFournisseur.this, Fournisseurs.class));
    }
}
