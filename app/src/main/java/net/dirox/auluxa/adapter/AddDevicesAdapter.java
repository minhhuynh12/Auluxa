package net.dirox.auluxa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


public abstract class AddDevicesAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private String[] recentlyAddedDevices;
    private String[] nameDevices;
    private int[] iconResouceId;
    private LayoutInflater inflater;

    public AddDevicesAdapter(Context context) {
        inflater = LayoutInflater.from(context);

        iconResouceId = new int[]{
                R.drawable.sonos,
                R.drawable.denon,

                R.drawable.sensor_2x,
                R.drawable.sensor_2x,

                R.drawable.doorlock_2x,
                R.drawable.alarm_2x,
                R.drawable.sensor_2x,
                R.drawable.camera_2x,
                R.drawable.video_door_fone_2x,
        };

        nameDevices = new String[]{
                "SONOS",
                "DENON",

                "HUMIDITY SENSOR",
                "TEMPRATURE SENSOR",

                "DOOR LOCK",
                "ALARM",
                "SENSOR",
                "CAMERA",
                "VIDEO DOOR PHONE"

        };

        recentlyAddedDevices = new String[]{
                "AA", "AB",
                "BA", "BB",
                "C1", "C2", "C3","C4", "C5" //22
        };
    }

    public abstract void onItemClicked(int position);

    @Override
    public int getCount() {
        return recentlyAddedDevices.length;
    }

    @Override
    public Object getItem(int position) {
        return recentlyAddedDevices[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_item_settings_climate_device, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.tvItemClimateDevice);
            holder.icon = (ImageView) convertView.findViewById(R.id.imgClimateDevice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // TODO: MinhHuynh: Text of row
        holder.text.setText(nameDevices[position]);
        holder.icon.setImageResource(iconResouceId[position]);

        convertView.findViewById(R.id.tvItemClimateDevice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(position);
            }
        });

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.sticky_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.headerText);
            holder.rightNumber = (TextView) convertView.findViewById(R.id.headerNumber);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        //Log.i("KKK pos", position + "");
        String headerText = "SHADES";
        String headerNumber = "3";

        switch (position){
            case 0:
                headerText = "AUDIO";
                headerNumber = "2";
                break;
            case 2:
                headerText = "CLIMATE";
                headerNumber = "2";
                break;
            case 4:
                headerText = "SECURITY";
                headerNumber = "5";
                break;

        }

        holder.text.setText(headerText);
        holder.rightNumber.setText(headerNumber);

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return recentlyAddedDevices[position].subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
        TextView rightNumber;
    }

    class ViewHolder {
        TextView text;
        ImageView icon;
    }

}