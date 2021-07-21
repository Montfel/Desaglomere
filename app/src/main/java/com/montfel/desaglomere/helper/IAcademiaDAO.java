package com.montfel.desaglomere.helper;

import com.montfel.desaglomere.model.Academia;

import java.util.List;

public interface IAcademiaDAO {

    public boolean create (Academia academia);
    public List<Academia> read ();
    public boolean update (Academia academia);
    public boolean delete (Academia academia);
}
