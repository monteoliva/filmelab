package br.com.monteoliva.filmelab.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import br.com.monteoliva.filmelab.R;
import br.com.monteoliva.filmelab.model.FilmeDataBean;
import br.com.monteoliva.filmelab.control.FilmeControl;
import br.com.monteoliva.filmelab.view.adapter.FilmeAdapter;

@EActivity(R.layout.activity_filme)
public class FilmeActivity extends AppCompatActivity {
    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewById(R.id.listView)
    protected ListView listView;

    @Bean
    protected FilmeAdapter adapter;

    @AfterViews
    public void afterViews() {
        // toolbar
        setSupportActionBar(toolbar);

        List<FilmeDataBean> list = new FilmeControl(this).getData().list();

        adapter.setList(list);

        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
    }

    @Click(R.id.fab)
    public void FloatingButton() {
        Log.i("FilmeActivity","Floating Button pressed");
    }

    /**
     * Evento KeyDown
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { finish(); return false; }
        else                                  { return super.onKeyDown(keyCode, event); }
    }
}