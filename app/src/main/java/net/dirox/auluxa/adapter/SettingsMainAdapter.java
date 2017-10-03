package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.SettingsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnq on 12/06/2017.
 */

public abstract class SettingsMainAdapter extends ArrayAdapter<SettingsItem> {

    private List<SettingsItem> list = new ArrayList<>();
    private Activity activity;
    private int row;

    public SettingsMainAdapter(Activity activity, int resource, List<SettingsItem> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.list = list;
        row = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);
            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if ((list == null) || ((position + 1) > list.size()))
            return view;

        final SettingsItem settingsItem = list.get(position);

        holder.parentView = view.findViewById(R.id.relativeLayout);

        holder.titleSettings = (TextView) view.findViewById(R.id.context);

        holder.titleSettings.setText(settingsItem.getTitleSettings());

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
            }
        });

        return view;
    }

    public abstract void onItemSelected(int position);

    public class ViewHolder {
        public View parentView;
        public TextView titleSettings;
    }


}