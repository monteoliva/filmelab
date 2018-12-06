package br.com.monteoliva.filmelab.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.monteoliva.filmelab.R;

@EActivity(R.layout.activity_fime_insert)
public class FilmeInsertActivity extends AppCompatActivity {
    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void afterViews() {
        // toolbar
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setSubtitle(R.string.title_insert);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

    }



    private void back() {
        startActivity(new Intent(this, FilmeActivity_.class));
        finish();
        overridePendingTransition( R.anim.lefttoright, R.anim.stable );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { back(); return false; }
        else                                  { return super.onKeyDown(keyCode, event); }
    }

    @Override
    public boolean onSupportNavigateUp() {
        back();
        return true;
    }
}