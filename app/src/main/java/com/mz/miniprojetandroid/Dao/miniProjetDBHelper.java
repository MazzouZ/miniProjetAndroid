package com.mz.miniprojetandroid.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mz.miniprojetandroid.Models.Fournisseur;
import com.mz.miniprojetandroid.Models.Medicament;
import com.mz.miniprojetandroid.Models.User;

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

    public static final String Medicament_TABLE_NAME = "medicament";
    public static final String Medicament_id = "id";
    public static final String Medicament_libelle = "libelle";
    public static final String Medicament_categorie = "categorie";
    public static final String Medicament_prix = "prix";
    public static final String Medicament_quantite = "quantite";
    public static final String Medicament_date = "date";

    public static final String User_TABLE_NAME = "user";
    public static final String User_id = "id";
    public static final String User_login = "login";
    public static final String User_password = "password";

    private SQLiteDatabase dbUser ;

    public miniProjetDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryF = " CREATE TABLE " + Fournisseur_TABLE_NAME + " (" +
                Fournisseurid + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Fournisseur_name + " TEXT NOT NULL, " +
                Fournisseur_email + " TEXT NOT NULL, " +
                Fournisseur_adresse + " TEXT NOT NULL, " +
                Fournisseur_telephone + " TEXT NOT NULL);";

        String queryM = " CREATE TABLE " + Medicament_TABLE_NAME + " (" +
                Medicament_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Medicament_libelle + " TEXT NOT NULL, " +
                Medicament_categorie + " TEXT NOT NULL, " +
                Medicament_prix + " TEXT NOT NULL, " +
                Medicament_quantite + " TEXT NOT NULL, " +
                Medicament_date + " TEXT NOT NULL);";

        String queryU = " CREATE TABLE " + User_TABLE_NAME + " (" +
                 User_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                User_login + " TEXT NOT NULL, " +
                User_password + " TEXT NOT NULL);";

        db.execSQL(queryF);
        db.execSQL(queryM);
        db.execSQL(queryU);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Fournisseur_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Medicament_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User_TABLE_NAME);
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

    public boolean saveNewMedicament(Medicament medicament) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Medicament_libelle, medicament.getLibelle());
        values.put(Medicament_categorie, medicament.getCategorie());
        values.put(Medicament_prix,medicament.getPrix());
        values.put(Medicament_quantite,medicament.getQuantite());
        values.put(Medicament_date,medicament.getDate());

        long result = db.insert(Medicament_TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public List<Medicament> medicamentList(String filter) {
        String query;
        if (filter.equals("")) {
            query = "SELECT  * FROM " + Medicament_TABLE_NAME;
        } else {
            query = "SELECT  * FROM " + Medicament_TABLE_NAME + " ORDER BY " + filter;
        }

        List<Medicament> medicamentLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Medicament medicament;
        if (cursor.moveToFirst()) {
            do {
                medicament = new Medicament();
                medicament.setId((int) cursor.getLong(cursor.getColumnIndex(Medicament_id)));
                medicament.setLibelle(cursor.getString(cursor.getColumnIndex(Medicament_libelle)));
                medicament.setCategorie(cursor.getString(cursor.getColumnIndex(Medicament_categorie)));
                medicament.setPrix(cursor.getString(cursor.getColumnIndex(Medicament_prix)));
                medicament.setQuantite(cursor.getString(cursor.getColumnIndex(Medicament_quantite)));
                medicament.setDate(cursor.getString(cursor.getColumnIndex(Medicament_date)));

                medicamentLinkedList.add(medicament);
            } while (cursor.moveToNext());
        }
        return medicamentLinkedList;
    }
    public Medicament getMedicament(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + Medicament_TABLE_NAME + " WHERE id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        Medicament medicament = new Medicament();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            medicament.setLibelle(cursor.getString(cursor.getColumnIndex(Medicament_libelle)));
            medicament.setCategorie(cursor.getString(cursor.getColumnIndex(Medicament_categorie)));
            medicament.setPrix(cursor.getString(cursor.getColumnIndex(Medicament_prix)));
            medicament.setQuantite(cursor.getString(cursor.getColumnIndex(Medicament_quantite)));
            medicament.setDate(cursor.getString(cursor.getColumnIndex(Medicament_date)));

        }
        return medicament;
    }
    public boolean deleteMedicamentRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        long result = db.delete(Medicament_TABLE_NAME, where, whereArgs);
        if (result == -1)
            return false;
        Toast.makeText(context, "Supprimer avec succes.", Toast.LENGTH_SHORT).show();
        return true;
    }
    public boolean updateMedicamentRecord(long medicamentId, Context context, Medicament updatedmedicament) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Medicament_libelle, updatedmedicament.getLibelle());
        values.put(Medicament_categorie, updatedmedicament.getCategorie());
        values.put(Medicament_prix, updatedmedicament.getPrix());
        values.put(Medicament_quantite, updatedmedicament.getQuantite());
        values.put(Medicament_date, updatedmedicament.getDate());

        String where = "id=?";
        String[] whereArgs = new String[]{String.valueOf(medicamentId)};
        long result = db.update(Medicament_TABLE_NAME, values, where, whereArgs);
        if (result == -1)
            return false;
        Toast.makeText(context, "modifié avec succes.", Toast.LENGTH_SHORT).show();
        return true;
    }
    public boolean saveNewUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User_login, user.getLogin());
        values.put(User_password, user.getPassword());

        long result = db.insert(User_TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public boolean checkUser(User u)
    {
        Cursor c=null;
        try {
            dbUser = this.getReadableDatabase();
            String sql="select * from user where login='"+u.getLogin()+"' and password='"+u.getPassword()+"'";
            c = dbUser.rawQuery(sql,null);

            return c.getCount() > 0;
        }
        finally {
            c.close();
        }
    }
}
