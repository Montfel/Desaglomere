package com.montfel.desaglomere.helper;

import com.montfel.desaglomere.model.Academia;

import java.util.List;

public interface IAcademiaDAO {

    boolean create(Academia academia);

    List<Academia> read();

    boolean update(Academia academia);

    boolean delete(Academia academia);
}
