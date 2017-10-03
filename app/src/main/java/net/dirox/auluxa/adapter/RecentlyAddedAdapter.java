package net.dirox.auluxa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


public abstract class RecentlyAddedAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private String[] recentlyAddedDevices;
    private String[] nameDevices;
    private int[] iconResouceId;
    private LayoutInflater inflater;

    public RecentlyAddedAdapter(Context context) {
        inflater = LayoutInflater.from(context);

        nameDevices = new String[]{
                "1 BUTTON ON/OFF",
                "2 BUTTON ON/OFF",
                "4 BUTTON ON/OFF",
                "ON/OFF MODE",
                "1 BUTTON DIMMING",
                "2 BUTTON DIMMING",
                "DIMMING NODE",
                "1 BUTTON PANEL",
                "2 BUTTON PANEL",
                "4 BUTTON PANEL",
                "1 BUTTON DIMMING PANEL",
                "2 BUTTON DIMMING PANEL",

                "1 BUTTON SHADES",
                "2 BUTTON SHADES",
                "1 BUTTON SHADES PANEL",
                "2 BUTTON SHADES PANEL",
                "SHADES NODE",

                "IR NODE NAME",
                "THERMOSTAT",
                "THERMOSTAT PANEL",
                "HUMIDITY SENSOR",
                "TEMPRATURE SENSOR",
                "TEMPRATURE & HUMIDITY SENSOR",

                "CABLE TV",
                "NOW TV",

                "AULUXA DOOR LOCK",
                "DOOR LOCK",
                "ALARM",
                "SENSOR",
                "DOORBELL"
        };

        iconResouceId = new int[]{
                R.drawable.one_button_2x,
                R.drawable.two_button_2x,
                R.drawable.four_button_2x,
                R.drawable.shades_node_2x,
                R.drawable.one_dimming_button_big_2x,
                R.drawable.two_dimming_button_2x,
                R.drawable.shades_node_2x,
                R.drawable.one_button_2x,
                R.drawable.two_button_2x,
                R.drawable.four_button_2x,
                R.drawable.one_dimming_button_big_2x,
                R.drawable.two_dimming_button_2x,

                R.drawable.one_button_2x,
                R.drawable.two_button_2x,
                R.drawable.one_button_2x,
                R.drawable.two_button_2x,
                R.drawable.shades_node_2x,

                R.drawable.ir_node,
                R.drawable.thrmostat_panel_2x,
                R.drawable.thrmostat_panel_2x,
                R.drawable.sensor_2x,
                R.drawable.temp_sensor_2x,
                R.drawable.sensor_2x,

                R.drawable.ir_node,
                R.drawable.ir_node,

                R.drawable.auluxa_door_lock_2x,
                R.drawable.doorlock_2x,
                R.drawable.shades_node_2x,
                R.drawable.sensor_2x,
                R.drawable.doorbell_2x,
        };

        recentlyAddedDevices = new String[]{
                "AA", "AB", "AC", "AD", "AE", "AF", "AG", "AH", "AI", "AK","AL", "AM", //11
                "BA", "BB", "BC", "BD", "BE", //16
                "C1", "C2", "C3","C4", "C5", "C6", //22
                "D1", "D2", //24
                "E1", "E2", "E3", "E4", "E5" //29
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
                headerText = "LIGHTS";
                headerNumber = "12";
                break;
            case 12:
                headerText = "SHADES";
                headerNumber = "5";
                break;
            case 17:
                headerText = "CLIMATE";
                headerNumber = "6";
                break;
            case 23:
                headerText = "VIDEO";
                headerNumber = "2";
                break;
            case 25:
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