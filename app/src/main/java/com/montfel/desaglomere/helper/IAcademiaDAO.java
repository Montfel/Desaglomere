package com.montfel.desaglomere.helper;

import com.montfel.desaglomere.model.AcademiaModel;

import java.util.List;

public interface IAcademiaDAO {

    public boolean create (AcademiaModel academiaModel);
    public List<AcademiaModel> read ();
    public boolean update (AcademiaModel academiaModel);
    public boolean delete (AcademiaModel academiaModel);
}
