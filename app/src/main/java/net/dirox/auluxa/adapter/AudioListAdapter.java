package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.AudioItem;

import java.util.List;

/**
 * Created by MyPC on 25/06/2017.
 */

public class AudioListAdapter extends ArrayAdapter<AudioItem> {
    private Context context;
    private List<AudioItem> audioItems;
    private int row;

    public AudioListAdapter(Context context, int resourse, List<AudioItem> audioItems) {
        super(context, resourse, audioItems);
        this.context = context;
        this.audioItems = audioItems;
        this.row = resourse;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);
            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if ((audioItems == null) || ((position + 1) > audioItems.size()))
            return view;
        final AudioItem item = audioItems.get(position);
        holder.imgAudioImage = (ImageView) view.findViewById(R.id.imgAudioImage);
        holder.tvAudio = (TextView) view.findViewById(R.id.textViewName);

        holder.imgAudioImage.setImageResource(item.photo);
        holder.tvAudio.setText(item.name);

        return view;
    }

    public class ViewHolder {

        public ImageView imgAudioImage;
        public TextView tvAudio;

    }

}