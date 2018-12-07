package br.com.monteoliva.filmelab.view.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.com.monteoliva.filmelab.R;
import br.com.monteoliva.filmelab.model.FilmeDataBean;

@EViewGroup(R.layout.item_view)
public class ItemView extends CardView {
    @ViewById(R.id.imgFilm)
    ImageView imageView;
    @ViewById(R.id.titleFilm)
    TextView title;
    @ViewById(R.id.genreFilm)
    TextView genre;
    @ViewById(R.id.yearFilm)
    TextView year;

    /**
     * Constructor
     *
     * @param context
     */
    public ItemView(@NonNull Context context) { super(context); }
    public ItemView(@NonNull Context context, @NonNull AttributeSet attrs) { super(context, attrs); }

    /**
     * Method to return Bind of this object
     *
     * @return
     */
    public ItemView bind(FilmeDataBean dataBean) {
        // load image
        Glide.with(getContext())
                .load(dataBean.getPoster())
                .into(imageView);

        title.setText(dataBean.getTitle());
        year.setText(dataBean.getYear());
        genre.setText(dataBean.getGenre());

        return this;
    }
}