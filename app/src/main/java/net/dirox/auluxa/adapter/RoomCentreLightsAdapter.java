package net.dirox.auluxa.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.LightItem;

import java.util.ArrayList;

/**
 * Created by an on 6/12/2017.
 */

public class RoomCentreLightsAdapter extends RecyclerView.Adapter<RoomCentreLightsAdapter.BindingHolder> {

    private Context mContext;
    private LayoutInflater mInflater;

    private int variableId;
    private ArrayList<LightItem> mLightItem;

    public RoomCentreLightsAdapter(Context context, int variableId, ArrayList<LightItem> items) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mLightItem = items;
        this.variableId = variableId;
    }

    public void turnAllLight(boolean isOn) {
        for (int i = 0; i < mLightItem.size(); i++) {
            if (mLightItem.get(i).isTurnedOn != isOn){
                mLightItem.get(i).isTurnedOn = isOn;
                notifyItemChanged(i);
            }
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mLightItem.size();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_light, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        LightItem item = mLightItem.get(position);

        SeekBar seekBar = (SeekBar) holder.getBinding().getRoot().findViewById(R.id.item_light_seekBar);

        holder.getBinding().getRoot().findViewById(R.id.item_light_btnPower).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLightItem.get(position).isTurnedOn = !mLightItem.get(position).isTurnedOn;
                notifyItemChanged(position);
            }
        });

        seekBar.setEnabled(item.isTurnedOn);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mLightItem.get(position).lightPercent = progress;
                notifyItemChanged(position);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        holder.getBinding().setVariable(variableId, item);
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
