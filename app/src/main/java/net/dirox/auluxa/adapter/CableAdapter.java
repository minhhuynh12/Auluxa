package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;

public class CableAdapter extends RecyclerView.Adapter<CableAdapter.ViewHolder> {


    public interface OnClickItemButtonListener {
        public void inClickButton(int pos);
    }

    OnClickItemButtonListener OnClickItemListener;
    ArrayList<String> strings;
    Context context;
    boolean hideItem;

    int select = -1;

    public CableAdapter(ArrayList<String> strings, OnClickItemButtonListener _onClickItemListener) {
        OnClickItemListener = _onClickItemListener;
        this.strings = strings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cable, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvNameButton.setText(strings.get(position));
        if (position == select) {
            holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.auluxaGreen));
            holder.ivArrow.setImageResource(R.drawable.scenes_right_arrow_rollover);
        } else {
            holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.BgDarker));
            holder.ivArrow.setImageResource(R.drawable.scenes_right_arrow);
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = position;
                notifyDataSetChanged();
                OnClickItemListener.inClickButton(position);

            }
        });
        if (position == (strings.size() - 1)) {
            if (Prefs.getIntPrefs(context, "CODE") != 0) {
                holder.tvCode.setVisibility(View.VISIBLE);
                holder.tvCode.setText(Prefs.getIntPrefs(context, "CODE")+"");
            } else holder.tvCode.setVisibility(View.GONE);

        } else holder.tvCode.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameButton;
        TextView tvCode;
        RelativeLayout relativeLayout;
        ImageView ivArrow;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNameButton = (TextView) itemView.findViewById(R.id.tvName);
            tvCode = (TextView) itemView.findViewById(R.id.tvCode);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.lnItem);
            ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
        }

    }

}