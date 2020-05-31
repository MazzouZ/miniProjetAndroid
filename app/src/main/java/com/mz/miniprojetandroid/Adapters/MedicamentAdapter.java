package com.mz.miniprojetandroid.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mz.miniprojetandroid.Controllers.Medicament.UpdateMedicament;
import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.Models.Medicament;
import com.mz.miniprojetandroid.R;

import java.util.List;

public class MedicamentAdapter extends RecyclerView.Adapter<MedicamentAdapter.ViewHolder> {
    private List<Medicament> mMedicamentList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView medicamentLibelle;
        public TextView medicamentCategorie;
        public TextView medicamentPrix;
        public TextView medicamentQuantite;
        public TextView medicamentDate;



        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            medicamentLibelle = (TextView) v.findViewById(R.id.listLibelle);
            medicamentCategorie = (TextView) v.findViewById(R.id.listCategorie);
            medicamentPrix = (TextView) v.findViewById(R.id.listPrix);
            medicamentQuantite = (TextView) v.findViewById(R.id.listQuantite);
            medicamentDate = (TextView) v.findViewById(R.id.listDate);


        }
    }

    public void add(int position, Medicament medicament) {
        mMedicamentList.add(position, medicament);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mMedicamentList.remove(position);
        notifyItemRemoved(position);
    }


    public MedicamentAdapter(List<Medicament> myDataset, Context context, RecyclerView recyclerView) {
        mMedicamentList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    @Override
    public MedicamentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_row_medicament, parent, false);
        MedicamentAdapter.ViewHolder vh = new MedicamentAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MedicamentAdapter.ViewHolder holder, final int position) {

        final Medicament medicament = mMedicamentList.get(position);
        holder.medicamentLibelle.setText("Libelle: " + medicament.getLibelle());
        holder.medicamentCategorie.setText("Categorie: " + medicament.getCategorie());
        holder.medicamentPrix.setText("Prix: " + medicament.getPrix());
        holder.medicamentQuantite.setText("Quantite: " + medicament.getQuantite());
        holder.medicamentDate.setText("Date: " + medicament.getDate());


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choisir une Option");
                builder.setMessage("Modifier ou Supprimer un Medicament ?");
                builder.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToUpdateActivity(medicament.getId());

                    }
                });
                builder.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        miniProjetDBHelper dbHelper = new miniProjetDBHelper(mContext);
                        dbHelper.deleteMedicamentRecord(medicament.getId(), mContext);

                        mMedicamentList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mMedicamentList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

    private void goToUpdateActivity(long medicamentId) {
        Intent goToUpdate = new Intent(mContext, UpdateMedicament.class);
        goToUpdate.putExtra("MedicamentId", medicamentId);
        mContext.startActivity(goToUpdate);
    }

    @Override
    public int getItemCount() {
        return mMedicamentList.size();
    }
}

