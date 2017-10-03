package net.dirox.auluxa.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.GatewayItem;

import java.util.List;

/**
 * Created by trungnq on 14/06/2017.
 */

public class GatewayListAdapter extends ArrayAdapter<GatewayItem> {

    private Activity activity;
    private List<GatewayItem> list;
    private int row;
    private int lastSelectIndex = -1;

    public GatewayListAdapter(Activity activity, int resource, List<GatewayItem> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.row = resource;
        this.list = list;
    }

    static class ViewHolder {
        ImageView imgControl;
        TextView tvGatewayName;
        TextView tvIsOnline;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(row, null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if ((list == null) || ((position + 1) > list.size()))
            return view;

        final GatewayItem gatewayItem = list.get(position);

        viewHolder.tvGatewayName = (TextView) view.findViewById(R.id.tvGatewayName);
        viewHolder.tvIsOnline = (TextView) view.findViewById(R.id.tvIsOnline);
        viewHolder.imgControl = (ImageView) view.findViewById(R.id.imgControl);
        viewHolder.tvGatewayName.setText(gatewayItem.getGatewayName());

        if (lastSelectIndex == position) {
            viewHolder.tvIsOnline.setVisibility(View.VISIBLE);
            viewHolder.tvIsOnline.setTextColor(Color.parseColor("#18bd9b"));
            viewHolder.tvGatewayName.setTextColor(Color.parseColor("#18bd9b"));
            viewHolder.imgControl.setImageResource(R.drawable.activeoff_bg_setupmain_navigation_login);
        } else {
            viewHolder.tvIsOnline.setVisibility(View.GONE);
            viewHolder.tvIsOnline.setTextColor(Color.parseColor("#3D424B"));
            viewHolder.tvGatewayName.setTextColor(Color.parseColor("#3D424B"));
            viewHolder.imgControl.setImageResource(R.drawable.activeon_bg_setup1_navigation_login);
        }

        viewHolder.imgControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastSelectIndex = position;

                GatewayListAdapter.this.notifyDataSetChanged();

            }
        });

        return view;
    }
}
