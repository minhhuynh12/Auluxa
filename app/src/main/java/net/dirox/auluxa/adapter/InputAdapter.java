package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;

import java.util.List;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.ViewHolder> {


    public interface OnClickItemListener {
        public void inClick();
    }

    OnClickItemListener OnClickItemListener;
    List<String> f;
    Context context;
    int select = -1;

    public InputAdapter(List<String> list, OnClickItemListener _onClickItemListener) {
        OnClickItemListener = _onClickItemListener;
        f = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_input, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvNameButton.setText(f.get(position).toString());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select != position) {
                    select = position;
                } else {
                    select = -1;
                }
                OnClickItemListener.inClick();
                notifyDataSetChanged();
            }
        });

        if (select == -1) {
            holder.linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.shape_background_btn_video));
            holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.auluxaGreen));
        } else {
            if (position == select) {
                holder.linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.shape_background_btn_video_select));
                holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.white));
            } else {
                holder.linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.shape_background_btn_video));
                holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.auluxaGreen));
            }
        }
    }

    @Override
    public int getItemCount() {
        return f.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameButton;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNameButton = (TextView) itemView.findViewById(R.id.tvName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lnItem);
        }

    }

    public void setSelect(int select1) {
        select = select1;
        notifyDataSetChanged();
    }

}