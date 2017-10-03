package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.SchedulingItem;
import net.dirox.auluxa.utils.swipe_layout.SwipeRevealLayout;
import net.dirox.auluxa.utils.swipe_layout.ViewBinderHelper;

import java.util.List;

/**
 * Created by trungnq on 12/06/2017.
 */

public abstract class SchedulingAdapter extends ArrayAdapter<SchedulingItem> {

    private List<SchedulingItem> list;
    private Activity activity;
    private int row;
    private final ViewBinderHelper binderHelper;

    public SchedulingAdapter(Activity activity, int resource, List<SchedulingItem> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.list = list;
        row = resource;
        binderHelper = new ViewBinderHelper();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        SchedulingAdapter.ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);
            holder = new SchedulingAdapter.ViewHolder();
            view.setTag(holder);
        } else {
            holder = (SchedulingAdapter.ViewHolder) view.getTag();
        }

        if ((list == null) || ((position + 1) > list.size()))
            return view;

        final SchedulingItem schedulingItem = list.get(position);

        holder.parentView = view.findViewById(R.id.relativeLayout);
        holder.titleSchedule = (TextView) view.findViewById(R.id.tvTitleSchedule);
        holder.deleteView = view.findViewById(R.id.delete_layout);
        holder.swipeRevealLayout = (SwipeRevealLayout) view.findViewById(R.id.swipe_layout);

        holder.titleSchedule.setText(schedulingItem.getTitleSchedule());

        binderHelper.bind(holder.swipeRevealLayout, String.valueOf(schedulingItem.getId()));

        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(schedulingItem);
            }
        });

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position, schedulingItem);
            }
        });

        return view;
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onSaveInstanceState(Bundle)}
     */
    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    public abstract void onItemSelected(int position, SchedulingItem item);

    public class ViewHolder {
        public View parentView;
        public TextView titleSchedule;
        public SwipeRevealLayout swipeRevealLayout;
        public View deleteView;
    }


}