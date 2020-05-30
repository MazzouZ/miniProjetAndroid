package com.mz.miniprojetandroid.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mz.miniprojetandroid.Controllers.Fournisseur.UpdateFournisseur;
import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.Models.Fournisseur;
import com.mz.miniprojetandroid.R;

import java.util.List;

public class FournisseurAdapter extends RecyclerView.Adapter<FournisseurAdapter.ViewHolder> {
    private List<Fournisseur> mFournisseurList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView fournisseurNom;
        public TextView fournisseurEmail;
        public TextView fournisseurAdresse;
        public TextView fournisseurTelephone;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            fournisseurNom = (TextView) v.findViewById(R.id.ListNom);
            fournisseurEmail = (TextView) v.findViewById(R.id.listEmail);
            fournisseurAdresse = (TextView) v.findViewById(R.id.ListAdresse);
            fournisseurTelephone = (TextView) v.findViewById(R.id.listTelephone);


        }
    }

    public void add(int position, Fournisseur fournisseur) {
        mFournisseurList.add(position, fournisseur);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mFournisseurList.remove(position);
        notifyItemRemoved(position);
    }


    public FournisseurAdapter(List<Fournisseur> myDataset, Context context, RecyclerView recyclerView) {
        mFournisseurList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    @Override
    public FournisseurAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Fournisseur fournisseur = mFournisseurList.get(position);
        holder.fournisseurNom.setText("Nom: " + fournisseur.getNom());
        holder.fournisseurEmail.setText("Email: " + fournisseur.getEmail());
        holder.fournisseurAdresse.setText("Adresse: " + fournisseur.getAdresse());
        holder.fournisseurTelephone.setText("Telephone: " + fournisseur.getTelephone());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choisir une Option");
                builder.setMessage("Modifier ou Supprimer un Fournisseur ?");
                builder.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToUpdateActivity(fournisseur.getId());

                    }
                });
                builder.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        miniProjetDBHelper dbHelper = new miniProjetDBHelper(mContext);
                        dbHelper.deleteFournisseurRecord(fournisseur.getId(), mContext);

                        mFournisseurList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mFournisseurList.size());
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

    private void goToUpdateActivity(long fournisseurId) {
        Intent goToUpdate = new Intent(mContext, UpdateFournisseur.class);
        goToUpdate.putExtra("FournisseurId", fournisseurId);
        mContext.startActivity(goToUpdate);
    }

    @Override
    public int getItemCount() {
        return mFournisseurList.size();
    }
}
