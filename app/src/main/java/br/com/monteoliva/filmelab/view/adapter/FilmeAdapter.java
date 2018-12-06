package br.com.monteoliva.filmelab.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import br.com.monteoliva.filmelab.model.FilmeDataBean;
import br.com.monteoliva.filmelab.view.custom.ItemView;
import br.com.monteoliva.filmelab.view.custom.ItemView_;

@EBean
public class FilmeAdapter extends BaseAdapter {
    @RootContext
    protected Context context;

    private List<FilmeDataBean> list;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public FilmeDataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final FilmeDataBean item = getItem(position);

        if (convertView == null) {
            row = ItemView_.build(context);
        }

        return ((ItemView) row).bind(item);
    }

    public void setList(List<FilmeDataBean> list) {
        this.list = list;
    }
}