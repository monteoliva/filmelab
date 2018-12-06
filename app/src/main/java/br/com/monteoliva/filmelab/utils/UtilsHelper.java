package br.com.monteoliva.filmelab.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class UtilsHelper {


    /**
     * Method to show SnackBar
     *
     * @param view
     * @param message
     */
    public void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}