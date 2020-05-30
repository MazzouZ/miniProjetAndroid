package com.mz.miniprojetandroid.Controllers.Fournisseur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mz.miniprojetandroid.Adapters.FournisseurAdapter;
import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.R;

public class Fournisseurs extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private miniProjetDBHelper dbHelper;
    private FournisseurAdapter adapter;
    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseurs);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(Fournisseurs.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        populaterecyclerView(filter);


    }

    private void populaterecyclerView(String filter){
        dbHelper = new miniProjetDBHelper(Fournisseurs.this);
        adapter = new FournisseurAdapter(dbHelper.fournisseurList(filter), Fournisseurs.this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);

        MenuItem item = menu.findItem(R.id.filterSpinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filterOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String filter = parent.getSelectedItem().toString();
                populaterecyclerView(filter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                populaterecyclerView(filter);
            }
        });


        spinner.setAdapter(adapter);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addMenu:
                goToAddUserActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToAddUserActivity(){
        Intent intent = new Intent(Fournisseurs.this, AddFournisseur.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
