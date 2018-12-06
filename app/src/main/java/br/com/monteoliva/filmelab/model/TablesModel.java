package br.com.monteoliva.filmelab.model;

/**
 * Classe que contem os scripts das tabelas
 * 
 * @author Claudio Monteoliva
 * @version 1.0
 * @copyright 2018 Monteoliva Developer
 * 
 */
public class TablesModel {
	// script da tabelas
	private String SCRIPT_TB_FILME = "CREATE TABLE tb_filme (id       integer      NOT NULL PRIMARY KEY autoincrement, " +
	                                 "                       title    varchar(200) default '', " +
	                                 "                       year     varchar(4)   default '', " +
	                                 "                       genre    varchar(50)  default '', " +
	                                 "                       director varchar(200) default '', " +
	                                 "                       actors   varchar(200) default '', " +
	                                 "                       plot     varchar(200) default '', " +
	                                 "                       poster   varchar(200) default '', " +
	                                 "                       imdbID   varchar(50)  default '');";

	// Script para fazer drop na tabela
	private String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS tb_filme;";

	// Cria as tabelas
	private String[] SCRIPT_DATABASE_CREATE = new String[] { SCRIPT_TB_FILME };


	public String[] getScriptCreate() { return this.SCRIPT_DATABASE_CREATE; }

	public String getScriptDelete() { return this.SCRIPT_DATABASE_DELETE; }

}