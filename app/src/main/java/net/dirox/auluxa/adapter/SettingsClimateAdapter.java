package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.SettingsClimateItem;

import java.util.List;

/**
 * Created by trungnq on 15/06/2017.
 */

public abstract class SettingsClimateAdapter extends ArrayAdapter<SettingsClimateItem> {

    private List<SettingsClimateItem> list;
    private Activity activity;
    private int row;
    private int lastSelectIndex = -1;

    public SettingsClimateAdapter(Activity activity, int resource, List<SettingsClimateItem> list, int defaul) {
        super(activity, resource, list);
        this.activity = activity;
        this.list = list;
        row = resource;
        lastSelectIndex = defaul;
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

        final SettingsClimateItem settingsClimateItem = list.get(position);

        holder.parentView = view.findViewById(R.id.rlSettingsClimate);
        holder.tvSettingsClimate = (TextView) view.findViewById(R.id.tvSettingsClimate);
        holder.isCheck = (CheckBox) view.findViewById(R.id.imgCheck);

        holder.tvSettingsClimate.setText(settingsClimateItem.getClimate());

        if (lastSelectIndex == position) {
            holder.tvSettingsClimate.setTextColor(Color.parseColor("#18bd9b"));
            holder.isCheck.setChecked(true);
        } else {
            holder.tvSettingsClimate.setTextColor(Color.parseColor("#B0000000"));
            holder.isCheck.setChecked(false);
        }

        holder.isCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
                lastSelectIndex = position;

                notifyDataSetChanged();
            }
        });

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
                lastSelectIndex = position;

                notifyDataSetChanged();
            }
        });

        return view;
    }

    public abstract void onItemSelected(int position);

    public class ViewHolder {
        private View parentView;
        public TextView tvSettingsClimate;
        public CheckBox isCheck;
    }


}