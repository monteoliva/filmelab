package br.com.monteoliva.filmelab.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import br.com.monteoliva.filmelab.R;
import br.com.monteoliva.filmelab.control.FilmeControl;
import br.com.monteoliva.filmelab.control.RestClient;
import br.com.monteoliva.filmelab.control.OnRestClientListener;
import br.com.monteoliva.filmelab.model.FilmeDataBean;
import br.com.monteoliva.filmelab.utils.UtilsHelper;

@EActivity(R.layout.activity_filme_insert)
@OptionsMenu(R.menu.menu_main)
public class FilmeInsertActivity extends AppCompatActivity implements OnRestClientListener {
    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    protected ActionBar actionBar;

    protected RestClient restClient;

    @ViewById(R.id.layout_insert)
    LinearLayout layoutInsert;

    @ViewById(R.id.edtSearch)
    EditText edtSearch;

    @ViewById(R.id.imgInsertFilm)
    ImageView imageView;
    @ViewById(R.id.titleInsertFilm)
    TextView titleView;
    @ViewById(R.id.yearInsertFilm)
    TextView yearView;
    @ViewById(R.id.genreInsertFilm)
    TextView genreView;
    @ViewById(R.id.directorInsertFilm)
    TextView directorView;
    @ViewById(R.id.actorsInsertFilm)
    TextView actorsView;
    @ViewById(R.id.plotInsertFilm)
    TextView plotView;

    protected FilmeDataBean filmeDataBean;

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

        restClient = new RestClient();
        restClient.setOnRestClientListener(this);
    }

    @Click(R.id.btnSearch)
    protected void search() {
        String txtSearch = edtSearch.getEditableText().toString();

        if (!txtSearch.isEmpty() && txtSearch != null) {
            searchAsync(txtSearch);
        }
    }

    @Background
    protected void searchAsync(final @NonNull String txtSearch) {
        restClient.send(txtSearch);
    }

    @UiThread
    protected void result(@NonNull FilmeDataBean dataBean) {
        Glide.with(this)
                .load(dataBean.getPoster())
                .into(this.imageView);

        this.titleView.setText(dataBean.getTitle());
        this.yearView.setText(dataBean.getYear());
        this.genreView.setText(dataBean.getGenre());
        this.directorView.setText(dataBean.getDirector());
        this.actorsView.setText(dataBean.getActors());
        this.plotView.setText(dataBean.getPlot());

        this.filmeDataBean = dataBean;
    }

    @OptionsItem(R.id.action_save)
    protected void save() {
        final long result = new FilmeControl(this).getData().insert(filmeDataBean);

        if (result > 0) {
            UtilsHelper.showSnackBar(layoutInsert, getString(R.string.message_insert));
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

    @Override
    public void onSuccess(FilmeDataBean dataBean) {
        result(dataBean);
    }

    @Override
    public void onError() { }
}