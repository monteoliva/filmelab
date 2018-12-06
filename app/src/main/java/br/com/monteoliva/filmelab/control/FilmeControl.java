package br.com.monteoliva.filmelab.control;

import android.content.Context;

import br.com.monteoliva.filmelab.model.FilmeDataDAO;

public class FilmeControl {
    private FilmeDataDAO dataDAO;

    public FilmeControl(Context context) {
        dataDAO = new FilmeDataDAO(context);
    }

    public FilmeDataDAO getData() { return dataDAO; }
}