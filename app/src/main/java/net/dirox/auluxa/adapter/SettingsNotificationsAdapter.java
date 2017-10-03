package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.NotificationsItem;

import java.util.List;

/**
 * Created by trungnq on 12/06/2017.
 */

public abstract class SettingsNotificationsAdapter extends ArrayAdapter<NotificationsItem> {

    private List<NotificationsItem> list;
    private Activity activity;
    private int row;

    public SettingsNotificationsAdapter(Activity activity, int resource, List<NotificationsItem> list) {
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

        final NotificationsItem notificationsItem = list.get(position);

        holder.parentView = view.findViewById(R.id.rlNotificationItem);

        holder.titleNotifications = (TextView) view.findViewById(R.id.context);

        holder.titleNotifications.setText(notificationsItem.getTitleNotifications());

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
        public TextView titleNotifications;
    }


}