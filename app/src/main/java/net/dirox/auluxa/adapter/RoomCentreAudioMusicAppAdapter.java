package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.dirox.auluxa.R;

/**
 * Created by an on 6/26/2017.
 */

public class RoomCentreAudioMusicAppAdapter extends RecyclerView.Adapter<RoomCentreAudioMusicAppAdapter.BindingHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private Integer[] arrayResourceId;

    public RoomCentreAudioMusicAppAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayResourceId = new Integer[]{R.drawable.ic_sound_cloud, R.drawable.ic_spotify};
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_audio_music_app, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        int resourceId = arrayResourceId[position];
        holder.imageView.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return arrayResourceId.length;
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BindingHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }
}
