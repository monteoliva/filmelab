package br.com.monteoliva.filmelab.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import br.com.monteoliva.filmelab.R;
import br.com.monteoliva.filmelab.model.CreateDataBase;

@EActivity(R.layout.splashscreen)
public class SplashScreen extends AppCompatActivity {
    private int segundos = 3000; // 3 segundos

    @AfterViews
    public void afterViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new CreateDataBase().create(getBaseContext());

                startActivity(new Intent(getBaseContext(), FilmeActivity_.class));
                finish();
                SplashScreen.this.overridePendingTransition( R.anim.righttoleft, R.anim.stable );
            }
        }, segundos);
    }

    /**
     * Evento KeyDown
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { return true; }
        else                                  { return super.onKeyDown(keyCode, event); }
    }
}