package br.com.monteoliva.filmelab.model;

import java.util.ArrayList;
import java.util.List;

// imports da API do ANDROID
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.com.monteoliva.filmelab.utils.Constantes;

public class FilmeDataDAO {
    protected SQLiteDatabase dba;

    /**
     * Constructor
     *
     * @param context
     */
    public FilmeDataDAO(Context context) {
        dba = context.openOrCreateDatabase(Constantes.NOME_BANCO, Context.MODE_PRIVATE, null);
    }

    /**
     * Method to insert film in DataBase
     *
     * @param tabela
     * @return
     */
    public long insert(FilmeDataBean tabela) {
        // seta os valores
        ContentValues valores = new ContentValues();
        valores.put("title"   , tabela.getTitle());
        valores.put("year"    , tabela.getYear());
        valores.put("genre"   , tabela.getGenre());
        valores.put("director", tabela.getDirector());
        valores.put("actors"  , tabela.getActors());
        valores.put("plot"    , tabela.getPlot());
        valores.put("poster"  , tabela.getPoster());
        valores.put("imdbID"  , tabela.getImdbID());

        // insere os dados na tabela
        return dba.insert(Constantes.TB_FILME, "", valores);
    }

    /**
     * method to update film in DataBase
     *
     * @param tabela
     * @return
     */
    public int update(FilmeDataBean tabela) {
        // seta os valores
        ContentValues valores = new ContentValues();
        valores.put("title"   , tabela.getTitle());
        valores.put("year"    , tabela.getYear());
        valores.put("genre"   , tabela.getGenre());
        valores.put("director", tabela.getDirector());
        valores.put("actors"  , tabela.getActors());
        valores.put("plot"    , tabela.getPlot());
        valores.put("poster"  , tabela.getPoster());
        valores.put("imdbID"  , tabela.getImdbID());

        // seta o WHERE
        final String where = "(id = " + tabela.getId() + ")";

        // atualiza a tabela e retorna
        return dba.update(Constantes.TB_FILME, valores, where, null);
    }

    /**
     * Method to delete film in DataBase
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        // monta o WHERE
        final String where = "(id = " + id + ")";

        // apaga e retorna
        return dba.delete(Constantes.TB_FILME, where, null);
    }

    //********************************************************************************************************************
    // Cursor
    //********************************************************************************************************************
    private Cursor getCursor() {
        try {
            // seta o Order By
            final String orderBy = "title ASC";

            // executa o SELECT
            return dba.query(Constantes.TB_FILME, FilmeDataBean.columns, null, null, null, null, orderBy);
        }
        catch (SQLException e) { return null; }
    }
    private Cursor getCursor(int id) {
        try {
            // seta o Where
            final String where = "(id = " + id + ")";

            // executa o SELECT
            return dba.query(Constantes.TB_FILME, FilmeDataBean.columns, where, null, null, null, null);
        }
        catch (SQLException e) { return null; }
    }

    //********************************************************************************************************************
    // List & select
    //********************************************************************************************************************
    /**
     * Metodo que retorna a listagem de perguntas
     *
     * @return
     */
    public List<FilmeDataBean> list() {
        final Cursor dados = getCursor();

        final List<FilmeDataBean> lista = new ArrayList<>();

        if (dados.getCount() > 0) {
            dados.moveToFirst();

            do {
                lista.add(setData(dados));
            }
            while (dados.moveToNext());
        }

        // fecha
        dados.close();

        // retorna a lista
        return lista;
    }

    public FilmeDataBean select(int id) {
        final Cursor dados = getCursor(id);

        FilmeDataBean table = new FilmeDataBean();

        if (dados.getCount() > 0) {
            dados.moveToFirst();

            do {
                table = setData(dados);
            }
            while (dados.moveToNext());
        }

        // fecha
        dados.close();

        return table;
    }

    private FilmeDataBean setData(Cursor data) {
        final FilmeDataBean tabela = new FilmeDataBean();
        tabela.setId(data.getInt(0));
        tabela.setTitle(data.getString(1));
        tabela.setYear(data.getString(2));
        tabela.setGenre(data.getString(3));
        tabela.setDirector(data.getString(4));
        tabela.setActors(data.getString(5));
        tabela.setPlot(data.getString(6));
        tabela.setPoster(data.getString(7));
        tabela.setImdbID(data.getString(8));

        return tabela;
    }

    public void close() {
        if (dba != null) { dba.close(); }
    }
}