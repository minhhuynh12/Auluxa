package net.dirox.auluxa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.CountryItem;

import java.util.ArrayList;

/**
 * Created by DoanThiPhuongHuyen on 07/08/2017.
 */

public class ListCountryAdapter extends BaseAdapter {

    private ArrayList<CountryItem> Country;
    private LayoutInflater layoutInflater;

    public ListCountryAdapter(ArrayList<CountryItem> country, Context context) {
        Country = country;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Country.size();
    }

    @Override
    public Object getItem(int i) {
        return Country.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        view = layoutInflater.inflate(R.layout.item_country_security_create_duration_pin, null);
        holder = new ViewHolder();
        holder.textViewCountry = (TextView) view.findViewById(R.id.textViewCountry);
        holder.textViewCountry.setText(Country.get(i).getName().toString());
        if(Country.get(i).getColorText() == 1)
        {
            holder.textViewCountry.setTextColor(Color.parseColor("#18bd9b"));
        }
        return view;
    }
    static class ViewHolder {
        TextView textViewCountry;
    }
}
