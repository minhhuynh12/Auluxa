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
import net.dirox.auluxa.data.sample.ClimateItem;

import java.util.List;

/**
 * Created by trungnq on 12/06/2017.
 */

public class RoomCentreClimateAdapter extends ArrayAdapter<ClimateItem> {

    private List<ClimateItem> list;
    private Activity activity;
    private int row;

    public RoomCentreClimateAdapter(Activity activity, int resource, List<ClimateItem> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.list = list;
        row = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        RoomCentreClimateAdapter.ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);
            holder = new RoomCentreClimateAdapter.ViewHolder();
            view.setTag(holder);
        } else {
            holder = (RoomCentreClimateAdapter.ViewHolder) view.getTag();
        }

        if ((list == null) || ((position + 1) > list.size()))
            return view;

        ClimateItem climateItem = list.get(position);

        holder.titleClimate = (TextView) view.findViewById(R.id.tvTitleClimate);
        holder.imgControl = (ImageView) view.findViewById(R.id.imgControl);

        holder.titleClimate.setText(climateItem.titleClimate);
        if (climateItem.isOn) {
            holder.imgControl.setImageResource(R.drawable.onoff_button_on_lights_lights_lights_balconylights);
        } else {
            holder.imgControl.setImageResource(R.drawable.onoff_button_off_lights_lights_lights_balconylights);
        }
        holder.imgControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                climateItem.isOn = !climateItem.isOn;
                notifyDataSetChanged();
            }
        });

        return view;
    }

    public class ViewHolder {
        public TextView titleClimate;
        public ImageView imgControl;
    }


}