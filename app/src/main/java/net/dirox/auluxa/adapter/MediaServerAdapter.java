package net.dirox.auluxa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.dirox.auluxa.R;

import java.util.ArrayList;
import java.util.List;

public class MediaServerAdapter extends RecyclerView.Adapter<MediaServerAdapter.ViewHolder> {

    List<String> f;

    public MediaServerAdapter(List<String> list) {
        f = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media_server, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvName.setText(f.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return f.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }

    public void reloadList(List<String> list) {
        f = list;
        notifyDataSetChanged();
    }

    public void filter(String keyword, ArrayList<String> list) {
        ArrayList<String> arraylist = new ArrayList<>();
        keyword = keyword.toLowerCase();
        if (keyword.length() == 0) {
            arraylist.addAll(list);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toLowerCase().contains(keyword)) {
                    arraylist.add(list.get(i));
                }
            }
            reloadList(arraylist);
        }
        notifyDataSetChanged();
    }

}