package com.mz.miniprojetandroid.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mz.miniprojetandroid.Models.Fournisseur;

import java.util.LinkedList;
import java.util.List;

public class miniProjetDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "miniProjet";
    private static final int DATABASE_VERSION = 1;
    public static final String Fournisseur_TABLE_NAME = "fournisseur";
    public static final String Fournisseurid = "id";
    public static final String Fournisseur_name = "nom";
    public static final String Fournisseur_email = "email";
    public static final String Fournisseur_adresse = "adresse";
    public static final String Fournisseur_telephone = "telephone";


    public miniProjetDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + Fournisseur_TABLE_NAME + " (" +
                Fournisseurid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Fournisseur_name + " TEXT NOT NULL, " +
                Fournisseur_email + " TEXT NOT NULL, " +
                Fournisseur_adresse + " TEXT NOT NULL, " +
                Fournisseur_telephone + " TEXT NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Fournisseur_TABLE_NAME);
        this.onCreate(db);
    }

    public boolean saveNewFournisseur(Fournisseur fournisseur) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Fournisseur_name, fournisseur.getNom());
        values.put(Fournisseur_email, fournisseur.getEmail());
        values.put(Fournisseur_adresse, fournisseur.getAdresse());
        values.put(Fournisseur_telephone, fournisseur.getTelephone());

        long result = db.insert(Fournisseur_TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public List<Fournisseur> fournisseurList(String filter) {
        String query;
        if (filter.equals("")) {
            query = "SELECT  * FROM " + Fournisseur_TABLE_NAME;
        } else {
            query = "SELECT  * FROM " + Fournisseur_TABLE_NAME + " ORDER BY " + filter;
        }

        List<Fournisseur> fournisseurLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Fournisseur fournisseur;
        if (cursor.moveToFirst()) {
            do {
                fournisseur = new Fournisseur();
                fournisseur.setId((int) cursor.getLong(cursor.getColumnIndex(Fournisseurid)));
                fournisseur.setNom(cursor.getString(cursor.getColumnIndex(Fournisseur_name)));
                fournisseur.setEmail(cursor.getString(cursor.getColumnIndex(Fournisseur_email)));
                fournisseur.setAdresse(cursor.getString(cursor.getColumnIndex(Fournisseur_adresse)));
                fournisseur.setTelephone(cursor.getString(cursor.getColumnIndex(Fournisseur_telephone)));
                fournisseurLinkedList.add(fournisseur);
            } while (cursor.moveToNext());
        }
        return fournisseurLinkedList;
    }

    public Fournisseur getFournisseur(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + Fournisseur_TABLE_NAME + " WHERE id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        Fournisseur fournisseur = new Fournisseur();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            fournisseur.setNom(cursor.getString(cursor.getColumnIndex(Fournisseur_name)));
            fournisseur.setEmail(cursor.getString(cursor.getColumnIndex(Fournisseur_email)));
            fournisseur.setAdresse(cursor.getString(cursor.getColumnIndex(Fournisseur_adresse)));
            fournisseur.setTelephone(cursor.getString(cursor.getColumnIndex(Fournisseur_telephone)));
        }
        return fournisseur;
    }

    public boolean deleteFournisseurRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        long result = db.delete(Fournisseur_TABLE_NAME, where, whereArgs);
        if (result == -1)
            return false;
        Toast.makeText(context, "Supprimer avec succes.", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean updateFournisseurRecord(long fournisseurId, Context context, Fournisseur updatedfournisseur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Fournisseur_name, updatedfournisseur.getNom());
        values.put(Fournisseur_email, updatedfournisseur.getEmail());
        values.put(Fournisseur_adresse, updatedfournisseur.getAdresse());
        values.put(Fournisseur_telephone, updatedfournisseur.getTelephone());
        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(fournisseurId)};
        long result = db.update(Fournisseur_TABLE_NAME, values, where, whereArgs);
        if (result == -1)
            return false;
        Toast.makeText(context, "modifié avec succes.", Toast.LENGTH_SHORT).show();
        return true;
    }
}
