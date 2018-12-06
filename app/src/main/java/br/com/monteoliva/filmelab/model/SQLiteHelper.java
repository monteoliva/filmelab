package br.com.monteoliva.filmelab.model;

// imports da API do ANDROID
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Implementacao de SQLiteOpenHelper
 * 
 * Classe utilitaria para abrir, criar, e atualizar o banco de dados
 * 
 * @author Claudio Monteoliva
 * @version 1.0
 * @copyright 2018 Monteoliva Developer
 * 
 */
public class SQLiteHelper extends SQLiteOpenHelper {
	private String[] scriptSQLCreate;
	private String scriptSQLDelete;

	/**
	 * Constructor que cria uma instancia de SQLiteHelper
	 * 
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco versao do banco de dados (se for diferente   para atualizar)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptSQLDelete SQL com o drop table...
	 */
	public SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String scriptSQLDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
	}

	/**
	 * Metodo para criar o Banco de Dados
	 * @param db
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		final int qtdeScripts = scriptSQLCreate.length;

		// Executa cada sql passado como parametro
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLCreate[i];
			
			// Cria o banco de dados executando o script de criacao
			db.execSQL(sql);
		}
	}

	/**
	 * Metodo de upgrade do Banco de Dados
	 *
	 * @param db
	 * @param versaoAntiga
	 * @param novaVersao
     */
	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		// Deleta as tabelas...
		db.execSQL(scriptSQLDelete);

		// Cria novamente...
		onCreate(db);
	}
}