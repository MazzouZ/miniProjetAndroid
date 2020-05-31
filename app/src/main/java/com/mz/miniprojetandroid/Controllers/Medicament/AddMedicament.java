package com.mz.miniprojetandroid.Controllers.Medicament;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.Models.Medicament;
import com.mz.miniprojetandroid.R;

public class AddMedicament extends AppCompatActivity {

    private EditText medicamentLibelle;
    private EditText medicamentCategorie;
    private EditText medicamentPrix;
    private EditText medicamentQuantite;
    private EditText medicamentDate;
    private Button mAddBtn;

    private miniProjetDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicament);

        medicamentLibelle = (EditText) findViewById(R.id.libelleAdd);
        medicamentCategorie = (EditText) findViewById(R.id.categorieAdd);
        medicamentPrix = (EditText) findViewById(R.id.prixAdd);
        medicamentQuantite = (EditText) findViewById(R.id.quantiteAdd);
        medicamentDate = (EditText) findViewById(R.id.dateAdd);

        mAddBtn = (Button) findViewById(R.id.Ajouter);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMedicament();
            }
        });
    }

    private void saveMedicament() {
        String libelle = medicamentLibelle.getText().toString().trim();
        String categorie = medicamentCategorie.getText().toString().trim();
        String prix = medicamentPrix.getText().toString().trim();
        String quantite = medicamentQuantite.getText().toString().trim();
        String date = medicamentDate.getText().toString().trim();
        dbHelper = new miniProjetDBHelper(AddMedicament.this);

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

        Medicament medicament = new Medicament(libelle,categorie,prix,quantite,date);
        dbHelper.saveNewMedicament(medicament);
        goBackHome();

    }

    private void goBackHome() {
        startActivity(new Intent(AddMedicament.this, Medicaments.class));
    }
}
