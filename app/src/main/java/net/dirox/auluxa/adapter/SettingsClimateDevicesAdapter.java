package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.SettingsClimateDevicesItem;

import java.util.List;

/**
 * Created by trungnq on 12/06/2017.
 */

public abstract class SettingsClimateDevicesAdapter extends ArrayAdapter<SettingsClimateDevicesItem> {

    private List<SettingsClimateDevicesItem> list;
    private Activity activity;
    private int row;

    private int positionClick = -1;

    public SettingsClimateDevicesAdapter(Activity activity, int resource, List<SettingsClimateDevicesItem> list) {
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

        final SettingsClimateDevicesItem item = list.get(position);

        holder.parentView = view.findViewById(R.id.rlItemClimateDevice);
        holder.climateDeviceName = (TextView) view.findViewById(R.id.tvItemClimateDevice);
        holder.imgClimateDevice = (ImageView) view.findViewById(R.id.imgClimateDevice);
        holder.imgArrowRight = (ImageView) view.findViewById(R.id.imgArrowRight);
        holder.climateDeviceName.setText(item.getClimateDeviceName());
        holder.imgClimateDevice.setImageResource(item.getResourdId());

//        if(position == positionClick){
//            if(holder.climateDeviceName.getCurrentTextColor() == ContextCompat.getColor(getContext(),R.color.BgDarker)){
//                holder.climateDeviceName.setTextColor(ContextCompat.getColor(getContext(),R.color.auluxaGreen));
//                holder.imgArrowRight.setImageResource(R.drawable.scenes_right_arrow_rollover);
//            }else {
//                holder.climateDeviceName.setTextColor(ContextCompat.getColor(getContext(),R.color.BgDarker));
//                holder.imgArrowRight.setImageResource(R.drawable.scenes_right_arrow);
//            }
//        }else{
//            holder.climateDeviceName.setTextColor(ContextCompat.getColor(getContext(),R.color.BgDarker));
//            holder.imgArrowRight.setImageResource(R.drawable.scenes_right_arrow);
//        }

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionClick = position;
                notifyDataSetChanged();
                onItemSelected(position);
            }
        });

        return view;
    }

    public abstract void onItemSelected(int position);

    public class ViewHolder {
        public View parentView;
        public TextView climateDeviceName;
        public ImageView imgClimateDevice;
        public ImageView imgArrowRight;
    }


}