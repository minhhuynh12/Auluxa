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
import net.dirox.auluxa.data.sample.TimerItem;

import java.util.List;

/**
 * Created by trungnq on 15/06/2017.
 */

public abstract class ClimateSleepTimerAdapter extends ArrayAdapter<TimerItem> {

    private List<TimerItem> list;
    private Activity activity;
    private int row;
    private int lastSelectIndex = -1;

    public ClimateSleepTimerAdapter(Activity activity, int resource, List<TimerItem> list) {
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

        final TimerItem timerItem = list.get(position);

        holder.parentView = view.findViewById(R.id.rlSleepTimer);
        holder.tvTimer = (TextView) view.findViewById(R.id.tvTimer);
        holder.isCheck = (CheckBox) view.findViewById(R.id.imgCheck);

        holder.tvTimer.setText(timerItem.getTimer());

        if (lastSelectIndex == position) {
            holder.tvTimer.setTextColor(Color.parseColor("#18bd9b"));
            holder.isCheck.setChecked(true);
        } else {
            holder.tvTimer.setTextColor(Color.parseColor("#B0000000"));
            holder.isCheck.setChecked(false);
        }

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
                lastSelectIndex = position;

                ClimateSleepTimerAdapter.this.notifyDataSetChanged();
            }
        });

        holder.isCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position);
                lastSelectIndex = position;

                ClimateSleepTimerAdapter.this.notifyDataSetChanged();
            }
        });


        return view;
    }

    public abstract void onItemSelected(int position);

    public class ViewHolder {
        private View parentView;
        public TextView tvTimer;
        public CheckBox isCheck;
    }


}