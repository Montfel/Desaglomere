package com.montfel.desaglomere.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.montfel.desaglomere.model.AcademiaModel;

import java.util.ArrayList;
import java.util.List;

public class AcademiaDAO implements IAcademiaDAO {

    private SQLiteDatabase escreve, le;

    public AcademiaDAO(Context context) {

        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean create(AcademiaModel academiaModel) {

        ContentValues cv = new ContentValues();
        cv.put("horario", academiaModel.getHorario());

        try {
            escreve.insert(DbHelper.TABELA_ACADEMIA, null, cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<AcademiaModel> read() {
        List<AcademiaModel> academias = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_ACADEMIA + " ;";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()) {
            AcademiaModel academiaModel = new AcademiaModel();

            Long id = c.getLong((c.getColumnIndex("id")));
            String horario = c.getString(c.getColumnIndex("horario"));

            academiaModel.setId(id);
            academiaModel.setHorario(horario);

            academias.add(academiaModel);
        }

        return academias;
    }

    @Override
    public boolean update(AcademiaModel academiaModel) {

        ContentValues cv = new ContentValues();
        cv.put("horario", academiaModel.getHorario());

        try {
            String[] args = {academiaModel.getId().toString()};
            escreve.update(DbHelper.TABELA_ACADEMIA, cv, "id = ?", args);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(AcademiaModel academiaModel) {

        try {
            String[] args = {academiaModel.getId().toString()};
            escreve.delete(DbHelper.TABELA_ACADEMIA, "id = ?", args);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
