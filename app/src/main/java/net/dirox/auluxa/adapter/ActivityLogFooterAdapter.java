package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.ActivityLogFooterItems;

import java.util.ArrayList;

/**
 * Created by MyPC on 18/07/2017.
 */

public class ActivityLogFooterAdapter extends RecyclerView.Adapter<ActivityLogFooterAdapter.ViewHolder> {
    Context context;
    ArrayList<ActivityLogFooterItems> activityLogFooterItemsesList;

    public interface OnClickListener{
        public void onClick(int pos);
    }

    OnClickListener onClickListener;

    public ActivityLogFooterAdapter(ArrayList<ActivityLogFooterItems> activityLogFooterItemses, OnClickListener onClickListener){
        this.activityLogFooterItemsesList = activityLogFooterItemses;
        this.onClickListener = onClickListener;
    }


    @Override
    public ActivityLogFooterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_security_activitilog_footer_list, parent , false);
        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityLogFooterAdapter.ViewHolder holder, int position) {
        holder.imgActivityLogFooter.setImageResource(activityLogFooterItemsesList.get(position).getImage());
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityLogFooterItemsesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgActivityLogFooter;
        RelativeLayout rlItem;
        public ViewHolder(View itemView) {
            super(itemView);
            imgActivityLogFooter = (ImageView) itemView.findViewById(R.id.imgActivityLogFooter);
            rlItem = (RelativeLayout) itemView.findViewById(R.id.rlItem);
        }
    }
}
