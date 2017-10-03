package net.dirox.auluxa.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.dirox.auluxa.BR;
import net.dirox.auluxa.R;
import net.dirox.auluxa.data.GeneratorSampleData;
import net.dirox.auluxa.data.sample.AudioDeviceItem;
import net.dirox.auluxa.widget.QuickAction.ActionItem;
import net.dirox.auluxa.widget.QuickAction.QuickAction;

import java.util.List;

/**
 * Created by an on 6/26/2017.
 */

public abstract class RoomCentreAudioDevicesAdapter extends RecyclerView.Adapter<RoomCentreAudioDevicesAdapter.BindingHolder> {

    private Context mContext;
    private List<AudioDeviceItem> arrayAudioDevice;

    public RoomCentreAudioDevicesAdapter(Context context) {
        mContext = context;
        arrayAudioDevice = GeneratorSampleData.audioDeviceItems;
    }

    public abstract void onMoreItemClick(int position);

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_audio_device, parent, false);
        return new RoomCentreAudioDevicesAdapter.BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        AudioDeviceItem item = arrayAudioDevice.get(position);
        holder.bind(item);
        holder.quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                onMoreItemClick(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayAudioDevice.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;
        ImageView btnMore;
        QuickAction quickAction;
        int addedXPos;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
            btnMore = (ImageView) binding.getRoot().findViewById(R.id.btnMore);

            quickAction = new QuickAction(v.getContext(), 2);
            quickAction.addActionItem(new ActionItem(1, "Settings", null));
            quickAction.addActionItem(new ActionItem(2, "Create group", null));

            addedXPos = (int) v.getContext().getResources().getDimension(R.dimen.distance_normal);
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quickAction.show(v, addedXPos);
                }
            });
        }

        public void bind(AudioDeviceItem audioItem) {
            binding.setVariable(BR.item, audioItem);
        }
    }
}
