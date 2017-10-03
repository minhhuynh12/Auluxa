package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.ActivityLogItems;

import java.util.ArrayList;

/**
 * Created by MyPC on 18/07/2017.
 */

public class ActivityLogAdapter extends RecyclerView.Adapter<ActivityLogAdapter.ViewHolder> {
    Context context;
    ArrayList<ActivityLogItems> activityLogItemsesList;

    public ActivityLogAdapter(ArrayList<ActivityLogItems> activityLogItemses){
        this.activityLogItemsesList = activityLogItemses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_security_activitylog_list, parent , false);
        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imgActivityLog.setImageResource(activityLogItemsesList.get(position).getImage());
        holder.txtActivityLogHeader.setText(activityLogItemsesList.get(position).getTxtheader());
        holder.txtActivityLogContent.setText(activityLogItemsesList.get(position).getTxtContent());

    }

    @Override
    public int getItemCount() {
        return activityLogItemsesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgActivityLog;
        TextView txtActivityLogHeader;
        TextView txtActivityLogContent;
        ImageView imgViewActivityLog;
        public ViewHolder(View itemView) {
            super(itemView);
            imgActivityLog = (ImageView) itemView.findViewById(R.id.imgActivityLog);
            txtActivityLogHeader = (TextView) itemView.findViewById(R.id.txtActivityLogHeader);
            txtActivityLogContent = (TextView) itemView.findViewById(R.id.txtActivityLogContent);
            //imgViewActivityLog = (ImageView) itemView.findViewById(R.id.imgViewActivityLog);
        }
    }

    public void updateList(ArrayList<ActivityLogItems> activityLogItemses){
        activityLogItemsesList = activityLogItemses;
        notifyDataSetChanged();
    }


}
