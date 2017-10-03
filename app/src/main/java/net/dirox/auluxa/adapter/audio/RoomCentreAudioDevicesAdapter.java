package net.dirox.auluxa.adapter.audio;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.AudioDeviceItem;
import net.dirox.auluxa.data.sample.AudioGroupDeviceItem;
import net.dirox.auluxa.widget.QuickAction.QuickAction;
import net.dirox.auluxa.widget.RecyclerViewExpandableAdapter;

import java.util.List;

/**
 * Created by an on 6/27/2017.
 */

public abstract class RoomCentreAudioDevicesAdapter extends RecyclerViewExpandableAdapter<GroupAudioDeviceHolder, AudioDeviceHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<AudioGroupDeviceItem> listAudioGroupDevice;

    public RoomCentreAudioDevicesAdapter(Context context, List<AudioGroupDeviceItem> list) {
        super(list);
        listAudioGroupDevice = list;
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<AudioGroupDeviceItem> list) {
        listAudioGroupDevice = list;
        notifyDataSetChanged();
    }

    public abstract void onMoreItemClick(int viewType, int groupPosition, int childPosition, int selectedIndex);

    public abstract void onMoreGroupItemClick(int groupPosition, int selectedIndex);

    @Override
    public GroupAudioDeviceHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_audio_device_group, parent, false);
        return new GroupAudioDeviceHolder(view);
    }

    @Override
    public AudioDeviceHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_audio_device, parent, false);
        return new AudioDeviceHolder(view);
    }

    @Override
    public void onBindChildViewHolder(AudioDeviceHolder holder, int groupPosition, ExpandableGroup group, int viewType, int childIndex) {
        AudioGroupDeviceItem groupDeviceItem = (AudioGroupDeviceItem) group;
        AudioDeviceItem audioDeviceItem;

        switch (viewType) {
            case AudioGroupDeviceItem.CHILD:
                initViewItemChild(holder);
                break;

            case AudioGroupDeviceItem.GROUP_ITEM_DETAIL:
                childIndex = 0;
                break;
        }

        audioDeviceItem = (AudioDeviceItem) groupDeviceItem.getItems().get(childIndex);
        holder.bind(audioDeviceItem);

        int finalChildIndex = childIndex;
        holder.quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                onMoreItemClick(viewType, groupPosition, finalChildIndex, pos);
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(GroupAudioDeviceHolder holder, int groupPosition, ExpandableGroup group) {
        //List<AudioGroupDeviceItem> list = (List<AudioGroupDeviceItem>) getGroups();
        AudioGroupDeviceItem groupDeviceItem = (AudioGroupDeviceItem) group;
        holder.bind(groupDeviceItem.getTitle(), groupDeviceItem.getItems().size());
        holder.quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                onMoreGroupItemClick(groupPosition, pos);
            }
        });
    }

    public void initViewItemChild(AudioDeviceHolder holder){
        holder.btnMore.setVisibility(View.GONE);
        holder.btnMoreBot.setVisibility(View.GONE);
        holder.btnMoreBot2.setVisibility(View.GONE);
        holder.llItem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorTransparent));
        holder.textView3.setTextColor(ContextCompat.getColor(mContext, R.color.BgDarker));
        holder.rlPin.setVisibility(View.GONE);
        holder.imageView8.setImageResource(R.drawable.audio_speaker_black);
    }
}


