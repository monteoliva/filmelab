package br.com.monteoliva.filmelab.control;

import br.com.monteoliva.filmelab.model.FilmeDataBean;

public interface OnRestClientListener {
    void onSuccess(FilmeDataBean dataBean);
    void onError();
}