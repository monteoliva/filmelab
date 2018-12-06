package br.com.monteoliva.filmelab.utils;

public interface Constantes {
    String URL_BASE        = "http://www.omdbapi.com/?apikey=cba26c2d&";
    String ENDPOINT_SEARCH = URL_BASE + "s=";
    String ENDPOINT_TITLE  = URL_BASE + "t=";

    String NOME_BANCO = "filme_lab";
    int VERSAO_BANCO  = 1;

    String TB_FILME = "tb_filme";
}