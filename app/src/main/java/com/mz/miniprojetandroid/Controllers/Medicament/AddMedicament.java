package com.mz.miniprojetandroid.Controllers.Medicament;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.Models.Fournisseur;
import com.mz.miniprojetandroid.Models.Medicament;
import com.mz.miniprojetandroid.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMedicament extends AppCompatActivity {

    private EditText medicamentLibelle;
    private EditText medicamentCategorie;
    private EditText medicamentPrix;
    private EditText medicamentQuantite;
    private EditText mDisplayDate;
    private Button mAddBtn;
    private Spinner spinner;
    private String filter = "";
    int fournisseurId;
    public String date;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "AddMedicament";

    private miniProjetDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicament);

        medicamentLibelle = (EditText) findViewById(R.id.libelleAdd);
        medicamentCategorie = (EditText) findViewById(R.id.categorieAdd);
        medicamentPrix = (EditText) findViewById(R.id.prixAdd);
        medicamentQuantite = (EditText) findViewById(R.id.quantiteAdd);
        mDisplayDate = (EditText) findViewById(R.id.dateAdd);

        mAddBtn = (Button) findViewById(R.id.Ajouter);

        spinner =findViewById(R.id.spinner);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMedicament();
            }
        });

        dbHelper = new miniProjetDBHelper(AddMedicament.this);

        List<Fournisseur> list = new ArrayList<Fournisseur>();

        list = dbHelper.fournisseurList(filter);

        ArrayAdapter<Fournisseur> adapter =
                new ArrayAdapter<Fournisseur>(getApplicationContext(),android.R.layout.simple_spinner_item,list);

        spinner.setAdapter(adapter);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddMedicament.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                 date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    private void saveMedicament() {
        String libelle = medicamentLibelle.getText().toString().trim();
        String categorie = medicamentCategorie.getText().toString().trim();
        String prix = medicamentPrix.getText().toString().trim();
        String quantite = medicamentQuantite.getText().toString().trim();
        String date = this.date;
        Fournisseur four = (Fournisseur) spinner.getSelectedItem();


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
        if(four == null){
            Toast.makeText(this, "Le fournisseur est obligatoire", Toast.LENGTH_SHORT).show();
        }
        else {
            fournisseurId = four.getId();
        }


        Medicament medicament = new Medicament(libelle,categorie,prix,quantite,date,fournisseurId);
        dbHelper.saveNewMedicament(medicament);
        goBackHome();

    }

    private void goBackHome() {
        startActivity(new Intent(AddMedicament.this, Medicaments.class));
    }
}
