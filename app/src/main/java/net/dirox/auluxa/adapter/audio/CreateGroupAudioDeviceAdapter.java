package net.dirox.auluxa.adapter.audio;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by an on 6/28/2017.
 */

public abstract class CreateGroupAudioDeviceAdapter extends RecyclerView.Adapter<CreateGroupAudioDeviceAdapter.BindingHolder> {

    private Context mContext;
    private List<AudioDeviceItem> arrayAudioDevice;
    private List<AudioDeviceItem> arrayAudioDeviceTransit;
    private HashMap<Integer, AudioDeviceItem> arraySelected;

    public CreateGroupAudioDeviceAdapter(Context context, ArrayList<AudioDeviceItem> arrayAudioDeviceInGroup) {
        mContext = context;
        arrayAudioDevice = GeneratorSampleData.audioDeviceItems;
        arraySelected = new HashMap<>();
        if (arrayAudioDeviceInGroup != null && arrayAudioDeviceInGroup.size() != 0) {
            arrayAudioDeviceTransit = arrayAudioDeviceInGroup;
        }
    }

    public abstract void onItemChangeSelectState(ArrayList<AudioDeviceItem> arraySelected);

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_audio_create_group, parent, false);
        return new CreateGroupAudioDeviceAdapter.BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        AudioDeviceItem item = arrayAudioDevice.get(position);
        holder.bind(item);
        if (arrayAudioDeviceTransit != null && arrayAudioDeviceTransit.size() != 0) {
            for (int i = 0; i < arrayAudioDeviceTransit.size(); i++) {
                if (arrayAudioDevice.get(position).id == arrayAudioDeviceTransit.get(i).id) {
                    holder.imageChecked.setSelected(true);
                    arraySelected.put(item.id, item);
                    break;
                }
                else holder.imageChecked.setSelected(arraySelected.containsKey(item.id));
            }
        }else holder.imageChecked.setSelected(arraySelected.containsKey(item.id));
        holder.imageChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arraySelected.containsKey(item.id) == true) {
                    arraySelected.remove(item.id);
                    holder.imageChecked.setSelected(false);
                } else {
                    arraySelected.put(item.id, item);
                    holder.imageChecked.setSelected(true);
                }
                ArrayList<AudioDeviceItem> tempList = new ArrayList<AudioDeviceItem>(arraySelected.values());
                onItemChangeSelectState(tempList);
            }
        });

        ArrayList<AudioDeviceItem> tempList = new ArrayList<AudioDeviceItem>(arraySelected.values());
        onItemChangeSelectState(tempList);

    }

    @Override
    public int getItemCount() {
        return arrayAudioDevice.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;
        ImageView imageChecked;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
            imageChecked = (ImageView) binding.getRoot().findViewById(R.id.imageChecked);
        }

        public void bind(AudioDeviceItem audioItem) {
            binding.setVariable(BR.item, audioItem);
        }
    }
}
