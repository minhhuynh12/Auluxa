package net.dirox.auluxa.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;

import java.util.ArrayList;


public abstract class ApplianceGridAdapter extends BaseAdapter {

    //public ArrayList<String> items;

    private LayoutInflater mInflater;

    private int rowHeight = 0;

    Fragment fragment;

    Context context;
    Resources resources;

    int[] listIcons = {
            R.drawable.ic_room_center_climate_selector,
            R.drawable.ic_room_center_lights_selector,
            R.drawable.ic_room_center_audio_selector,
            R.drawable.ic_room_center_video_selector,
            R.drawable.ic_room_center_shades_selector,
            R.drawable.ic_room_center_others_selector
    };

    ArrayList<String> listDevices = new ArrayList<String>() {{
        add("CLIMATE");
        add("LIGHTS");
        add("AUDIO");
        add("VIDEO");
        add("SHADES");
        add("OTHERS");
    }};

    public ApplianceGridAdapter(Fragment fragment, int rowHeight) {

        context = fragment.getContext();
        resources = context.getResources();
        this.rowHeight = rowHeight;

        mInflater = (LayoutInflater) fragment.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.fragment = fragment;
    }

    public abstract void onItemClicked(int position);

    @Override
    public int getCount() {
        return listDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return listDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.appliance_menu_item, null);
        } else {
            convertView = mInflater.inflate(R.layout.appliance_menu_item, null);
        }

        convertView.findViewById(R.id.grid_item_rootLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(position);
            }
        });

        ImageView iconImage = (ImageView) convertView.findViewById(R.id.grid_item_image);
        iconImage.setImageResource(listIcons[position]);
        //iconImage.setImageDrawable(resources.getDrawable(listIcons[position]));

        //TODO: change image later
        if (position == 5) {
            iconImage.setScaleType(ImageView.ScaleType.CENTER);
        }

        TextView menuTextView = (TextView) convertView.findViewById(R.id.grid_menu_label);
        menuTextView.setText(listDevices.get(position));

        int height = this.rowHeight;//
        convertView.setMinimumHeight(height);

        return convertView;
    }

}
