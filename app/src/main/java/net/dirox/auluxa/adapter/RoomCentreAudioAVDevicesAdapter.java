package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.AudioItem;

import java.util.ArrayList;

/**
 * Created by an on 6/26/2017.
 */

public class RoomCentreAudioAVDevicesAdapter extends RecyclerView.Adapter<RoomCentreAudioAVDevicesAdapter.BindingHolder> {

    private Context mContext;
    private ArrayList<AudioItem> audioItems;

    public interface OnItemClick {
        public void onItemSelected(int position);
    }

    OnItemClick onItemClick;

    public RoomCentreAudioAVDevicesAdapter(Context context, ArrayList<AudioItem> audioItems, OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
        mContext = context;
        this.audioItems = audioItems;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_audio_av_device, parent, false);
        return new RoomCentreAudioAVDevicesAdapter.BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        AudioItem audioItem = audioItems.get(position);
        holder.bind(audioItem);

        holder.imageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioItems.get(position).isSelected = !audioItems.get(position).isSelected;
                holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.auluxaGreen));
                holder.imageIcon.setImageResource(R.drawable.av_device);
                onItemClick.onItemSelected(position);

            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioItems.get(position).isSelected = !audioItems.get(position).isSelected;
                if (holder.textView.getCurrentTextColor() == ContextCompat.getColor(mContext, R.color.auluxaGreen)) {
                    holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.BgDarker));
                    holder.imageIcon.setImageResource(R.drawable.av_device_not_select);
                } else {
                    holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.auluxaGreen));
                    holder.imageIcon.setImageResource(R.drawable.av_device);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return audioItems.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageIcon;
        ConstraintLayout constraintItem;

        public BindingHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
            constraintItem = (ConstraintLayout) v.findViewById(R.id.constraintItem);
            imageIcon = (ImageView) v.findViewById(R.id.imageIcon);
        }

        public void bind(AudioItem audioItem) {
            textView.setText(audioItem.name);
        }
    }
}
