package com.mz.miniprojetandroid.Controllers.Medicament;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mz.miniprojetandroid.Controllers.Fournisseur.Fournisseurs;
import com.mz.miniprojetandroid.Controllers.Fournisseur.UpdateFournisseur;
import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.Models.Fournisseur;
import com.mz.miniprojetandroid.Models.Medicament;
import com.mz.miniprojetandroid.R;

public class UpdateMedicament extends AppCompatActivity {

    private EditText medicamentLibelle;
    private EditText medicamentCategorie;
    private EditText medicamentPrix;
    private EditText medicamentQuantite;
    private EditText medicamentDate;
    private Button mUpdateBtn;
    private miniProjetDBHelper dbHelper;
    private long receivedMedicamentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicament);

        medicamentLibelle = (EditText) findViewById(R.id.libelleUpdate);
        medicamentCategorie = (EditText) findViewById(R.id.categorieUpdate);
        medicamentPrix = (EditText) findViewById(R.id.prixUpdate);
        medicamentQuantite = (EditText) findViewById(R.id.quantiteUpdate);
        medicamentDate = (EditText) findViewById(R.id.dateUpdate);
        mUpdateBtn = (Button) findViewById(R.id.modifier);
        dbHelper = new miniProjetDBHelper(UpdateMedicament.this);

        try {
            receivedMedicamentId = getIntent().getLongExtra("MedicamentId", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Medicament queriedMedicament = dbHelper.getMedicament(receivedMedicamentId);
        medicamentLibelle.setText(queriedMedicament.getLibelle());
        medicamentCategorie.setText(queriedMedicament.getCategorie());
        medicamentPrix.setText(queriedMedicament.getPrix());
        medicamentQuantite.setText(queriedMedicament.getQuantite());
        medicamentDate.setText(queriedMedicament.getDate());

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateMedicament();
            }
        });
    }

    private void updateMedicament() {
        String libelle = medicamentLibelle.getText().toString().trim();
        String categorie = medicamentCategorie.getText().toString().trim();
        String prix = medicamentPrix.getText().toString().trim();
        String quantite = medicamentQuantite.getText().toString().trim();
        String date = medicamentDate.getText().toString().trim();
        dbHelper = new miniProjetDBHelper(UpdateMedicament.this);

        if (libelle.isEmpty()) {
            Toast.makeText(this, "Le libelle est obligatoire", Toast.LENGTH_SHORT).show();
        }

        if (categorie.isEmpty()) {
            Toast.makeText(this, "La categorie est obligatoire", Toast.LENGTH_SHORT).show();
        }

        if (prix.isEmpty()) {
            Toast.makeText(this, "Le prix est obligatoire", Toast.LENGTH_SHORT).show();
        }

        if (quantite.isEmpty()) {
            Toast.makeText(this, "La quantite est obligatoire", Toast.LENGTH_SHORT).show();
        }

        if (date.isEmpty()) {
            Toast.makeText(this, "La date est obligatoire", Toast.LENGTH_SHORT).show();
        }
        Medicament updatedMedicament = new Medicament(libelle,categorie,prix,quantite,date);

        dbHelper.updateMedicamentRecord(receivedMedicamentId, UpdateMedicament.this, updatedMedicament);
        goBackHome();

    }

    private void goBackHome() {
        startActivity(new Intent(UpdateMedicament.this, Medicaments.class));
    }
}
