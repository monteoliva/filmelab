package br.com.monteoliva.filmelab.control;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

import br.com.monteoliva.filmelab.utils.Constantes;

@Rest(rootUrl = Constantes.URL_BASE, converters = {GsonHttpMessageConverter.class})
public interface RestCllient {
    @Get("t={searchString}")
    FilmeJson getResult(@Path String searchString);
}