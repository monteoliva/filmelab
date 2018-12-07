package br.com.monteoliva.filmelab.control;


import android.support.annotation.NonNull;

import com.google.gson.Gson;

import br.com.monteoliva.filmelab.model.FilmeDataBean;
import br.com.monteoliva.filmelab.utils.http.HttpGet;
import br.com.monteoliva.filmelab.utils.http.HttpResultBean;

public class RestClient {
    private OnRestClientListener listener;

    public void send(@NonNull String textSearch) {
        HttpGet httpGet = new HttpGet();
        httpGet.add("apikey", "cba26c2d");
        httpGet.add("t", textSearch);

        HttpResultBean resultBean = httpGet.send("http://www.omdbapi.com/");

        FilmeJson result = new Gson().fromJson(resultBean.getMessage(), FilmeJson.class);

        if (result != null) {
            FilmeDataBean dataBean = new FilmeDataBean();
            dataBean.setActors(result.getActors());
            dataBean.setDirector(result.getDirector());
            dataBean.setGenre(result.getGenre());
            dataBean.setPlot(result.getPlot());
            dataBean.setTitle(result.getTitle());
            dataBean.setYear(result.getYear());
            dataBean.setPoster(result.getPoster());
            dataBean.setImdbID(result.getImdbID());

            if (listener != null) { listener.onSuccess(dataBean); }
        }
        else {
            if (listener != null) { listener.onError(); }
        }
    }

    /**
     * Method Setter
     *
     * @param listener
     */
    public void setOnRestClientListener(OnRestClientListener listener) { this.listener = listener; }
}