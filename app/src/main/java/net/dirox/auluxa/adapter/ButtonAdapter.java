package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {


    public interface OnClickItemButtonListener {
        public void inClickButton();
    }

    OnClickItemButtonListener OnClickItemListener;
    int f;
    Context context;

    int select = -1;

    public ButtonAdapter(int f, OnClickItemButtonListener _onClickItemListener) {
        OnClickItemListener = _onClickItemListener;
        this.f = f;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvNameButton.setText("BUTTON " + (position + 1));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = position;
                notifyDataSetChanged();
                OnClickItemListener.inClickButton();

            }
        });
        if (position == select) {
            holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.auluxaGreen));
            holder.ivArrow.setImageResource(R.drawable.scenes_right_arrow_rollover);
        } else {
            holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.BgDarker));
            holder.ivArrow.setImageResource(R.drawable.scenes_right_arrow);
        }

    }

    @Override
    public int getItemCount() {
        return f;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameButton;
        RelativeLayout relativeLayout;
        ImageView ivArrow;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNameButton = (TextView) itemView.findViewById(R.id.tvName);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.lnItem);
            ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
        }

    }

}