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

import java.util.ArrayList;

/**
 * Created by trungnq on 12/06/2017.
 */

public abstract class MainHomeAdapter extends ArrayAdapter<String> {

    ArrayList<String> list;
    private Activity activity;
    private int row;
    private int lastSelectIndex = -1;

    public MainHomeAdapter(Activity activity, int resource, ArrayList<String> list) {
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

        final String scenesName = list.get(position);

        holder.titleScenes = (TextView) view.findViewById(R.id.tvTitleScenes);
        holder.imgScenesRightArrow = (ImageView) view.findViewById(R.id.imgScenesRightArrow);

        holder.titleScenes.setText(scenesName);

        if (lastSelectIndex == position) {
            holder.titleScenes.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
            holder.imgScenesRightArrow.setImageResource(R.drawable.scenes_right_arrow_rollover);
        } else {
            holder.titleScenes.setTextColor(ContextCompat.getColor(getContext(), R.color.BgDarker));
            holder.imgScenesRightArrow.setImageResource(R.drawable.scenes_right_arrow);
        }

        holder.titleScenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastSelectIndex = position;
                notifyDataSetChanged();
            }
        });

        holder.imgScenesRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.titleScenes.setTextColor(ContextCompat.getColor(getContext(), R.color.auluxaGreen));
                holder.imgScenesRightArrow.setImageResource(R.drawable.scenes_right_arrow_rollover);
                arrowClick(position);
            }
        });

        return view;
    }

    public abstract void arrowClick(int position);

    public class ViewHolder {
        public TextView titleScenes;
        public ImageView imgScenesRightArrow;
    }
}
