package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.SettingsTimezoneItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnq on 15/06/2017.
 */

public abstract class SettingsTimeZoneAdapter extends ArrayAdapter<SettingsTimezoneItem> {

    private List<SettingsTimezoneItem> list;
    private ArrayList<SettingsTimezoneItem> arraylist;
    private Activity activity;
    private int row;

    public SettingsTimeZoneAdapter(Activity activity, int resource, List<SettingsTimezoneItem> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.list = list;
        row = resource;
        this.arraylist = new ArrayList<SettingsTimezoneItem>();
        this.arraylist.addAll(list);
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

        final SettingsTimezoneItem settingsTimezoneItem = list.get(position);

        holder.tvTimeZone = (TextView) view.findViewById(R.id.tvTimeZone);
        holder.parentView = view.findViewById(R.id.rlTimeZone);

        holder.tvTimeZone.setText(settingsTimezoneItem.getTimeZone());

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
            }
        });

        return view;
    }

    public abstract void onItemSelected(int position);

    /*Filter*/
    public void filter(String keyword) {
        keyword = keyword.toLowerCase();
        list.clear();
        if (keyword.length() == 0) {
            list.addAll(arraylist);
        } else {
            for (SettingsTimezoneItem item : arraylist) {
                if (item.getTimeZone().toLowerCase().contains(keyword)) {
                    list.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public View parentView;
        public TextView tvTimeZone;
    }


}