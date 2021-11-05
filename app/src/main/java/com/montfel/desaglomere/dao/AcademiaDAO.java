package com.montfel.desaglomere.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.montfel.desaglomere.helper.DbHelper;
import com.montfel.desaglomere.helper.IAcademiaDAO;
import com.montfel.desaglomere.model.Academia;

import java.util.ArrayList;
import java.util.List;

public class AcademiaDAO implements IAcademiaDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public AcademiaDAO(Context context) {

        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean create(Academia academia) {

        ContentValues cv = new ContentValues();
        cv.put("horario", academia.getHorario());

        try {
            escreve.insert(DbHelper.TABELA_ACADEMIA, null, cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Academia> read() {
        List<Academia> academias = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_ACADEMIA + " ;";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()) {
            Academia academia = new Academia();

            Long id = c.getLong((c.getColumnIndex("id")));
            String horario = c.getString((c.getColumnIndex("horario")));

            academia.setId(id);
            academia.setHorario(horario);

            academias.add(academia);
        }

        return new ArrayList<>(academias); //envia uma cópia
    }

    @Override
    public boolean update(Academia academia) {

        ContentValues cv = new ContentValues();
        cv.put("horario", academia.getHorario());

        try {
            String[] args = {academia.getId().toString()};
            escreve.update(DbHelper.TABELA_ACADEMIA, cv, "id = ?", args);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Academia academia) {

        try {
            String[] args = {academia.getId().toString()};
            escreve.delete(DbHelper.TABELA_ACADEMIA, "id = ?", args);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
