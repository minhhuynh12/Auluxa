package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.SettingsClimateThermostatPanelItem;

import java.util.List;

/**
 * Created by trungnq on 15/06/2017.
 */

public abstract class SettingsClimateDeviceThermostatPanelAdapter extends ArrayAdapter<SettingsClimateThermostatPanelItem> {

    private List<SettingsClimateThermostatPanelItem> list;
    private Activity activity;
    private int row;
    private int lastSelectIndex = -1;

    public SettingsClimateDeviceThermostatPanelAdapter(Activity activity, int resource, List<SettingsClimateThermostatPanelItem> list) {
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

        final SettingsClimateThermostatPanelItem item = list.get(position);

        holder.parentView = view.findViewById(R.id.rlSettingsClimateThermostatPanel);
        holder.tvThermostatPanelName = (TextView) view.findViewById(R.id.tvThermostatPanelName);
        holder.imgCheck = (ImageView) view.findViewById(R.id.imgCheck);

        holder.tvThermostatPanelName.setText(item.getThermostatPanelName());

        if (lastSelectIndex == position) {
            holder.imgCheck.setImageResource(R.drawable.scenes_checkbox_filled);
        } else {
            holder.imgCheck.setImageResource(R.drawable.scenes_checkbox_off);
        }

        holder.imgCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
                lastSelectIndex = position;

                SettingsClimateDeviceThermostatPanelAdapter.this.notifyDataSetChanged();
            }
        });

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
                lastSelectIndex = position;

                SettingsClimateDeviceThermostatPanelAdapter.this.notifyDataSetChanged();
            }
        });

        return view;
    }

    public abstract void onItemSelected(int position);

    public class ViewHolder {
        private View parentView;
        public TextView tvThermostatPanelName;
        public ImageView imgCheck;
    }


}