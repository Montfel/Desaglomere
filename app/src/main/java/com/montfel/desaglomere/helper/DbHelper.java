package com.montfel.desaglomere.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String NOME_DB = "DB_AMBIENTES";
    public static final String TABELA_ACADEMIA = "academia";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_ACADEMIA +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " horario VARCHAR(5) NOT NULL);";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        } catch (Exception e) {
            Log.e("ERROR DB", "Falha ao criar a tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_ACADEMIA + " ;";

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar o APP");
        } catch (Exception e) {
            Log.e("ERROR DB", "Falha ao atualizar o APP" + e.getMessage());
        }
    }
}
