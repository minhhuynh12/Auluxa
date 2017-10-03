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
import net.dirox.auluxa.data.sample.SettingsNotificationsEnergyItem;

import java.util.List;

/**
 * Created by trungnq on 15/06/2017.
 */

public abstract class SettingsNotificationsEnergyAdapter extends ArrayAdapter<SettingsNotificationsEnergyItem> {

    private List<SettingsNotificationsEnergyItem> list;
    private Activity activity;
    private int row;
    private int lastSelectIndex = -1;
    private boolean[] arraySelected;

    public SettingsNotificationsEnergyAdapter(Activity activity, int resource, List<SettingsNotificationsEnergyItem> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.list = list;
        row = resource;
    }

    public void checkAll(boolean checked) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).checked = checked;
        }
        notifyDataSetChanged();
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

        final SettingsNotificationsEnergyItem item = list.get(position);

        arraySelected = new boolean[list.size()];

        holder.parentView = view.findViewById(R.id.rlNotificationsEngery);
        holder.tvNotificationsEngery = (TextView) view.findViewById(R.id.tvNotificationsEngery);
        holder.isCheck = (CheckBox) view.findViewById(R.id.imgCheck);

        holder.tvNotificationsEngery.setText(item.getEnergy());
        holder.isCheck.setChecked(item.isChecked());

        if (item.isChecked()) {
            holder.tvNotificationsEngery.setTextColor(Color.parseColor("#18bd9b"));
        } else {
            holder.tvNotificationsEngery.setTextColor(Color.parseColor("#B0000000"));
        }

        holder.isCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);

                if (arraySelected[list.get(position).getId()]) {
                    holder.isCheck.setChecked(false);
                    holder.tvNotificationsEngery.setTextColor(Color.parseColor("#B0000000"));
                    arraySelected[list.get(position).getId()] = false;
                } else {
                    holder.isCheck.setChecked(true);
                    holder.tvNotificationsEngery.setTextColor(Color.parseColor("#18bd9b"));
                    arraySelected[list.get(position).getId()] = true;
                }

            }
        });

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
                if (arraySelected[list.get(position).getId()]) {
                    holder.isCheck.setChecked(false);
                    arraySelected[list.get(position).getId()] = false;
                    holder.tvNotificationsEngery.setTextColor(Color.parseColor("#B0000000"));
                } else {
                    holder.isCheck.setChecked(true);
                    arraySelected[list.get(position).getId()] = true;
                    holder.tvNotificationsEngery.setTextColor(Color.parseColor("#18bd9b"));
                }

            }
        });

        return view;
    }

    public abstract void onItemSelected(int position);

    public class ViewHolder {
        private View parentView;
        public TextView tvNotificationsEngery;
        public CheckBox isCheck;
    }


}