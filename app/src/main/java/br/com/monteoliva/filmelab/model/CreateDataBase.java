package br.com.monteoliva.filmelab.model;

// imports da API do ANDROID
import android.content.Context;

import br.com.monteoliva.filmelab.utils.Constantes;

/**
 * Class to create the SQLite DataBase
 * 
 * @author Claudio Monteoliva
 * @version 1.0
 * @copyright 2018 Monteoliva Developer
 * 
 */
public class CreateDataBase {
	/**
	 * Method to create the SQLite DataBase
	 */
	public void create(Context ctx) {
		final TablesModel tabelasDAO = new TablesModel();
		final SQLiteHelper dbHelper = new SQLiteHelper(ctx, Constantes.NOME_BANCO, Constantes.VERSAO_BANCO, tabelasDAO.getScriptCreate(), tabelasDAO.getScriptDelete());

		dbHelper.getWritableDatabase();

		// fecha a conexao
		if (dbHelper != null) { dbHelper.close(); }
	}
}