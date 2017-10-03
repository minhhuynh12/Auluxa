package net.dirox.auluxa.adapter.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.BR;
import net.dirox.auluxa.R;

/**
 * Created by an on 6/29/2017.
 */

public class ScenesCreateRoomVideoListAdapter extends RecyclerView.Adapter<ScenesCreateRoomVideoListAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] arrayString;
    private ItemClickListener listener;

    public ScenesCreateRoomVideoListAdapter(Context context, String[] arrayString, ItemClickListener listener) {
        this.mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayString = arrayString;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_scenes_room_video, parent, false);
        ViewHolder viewHolder = new ScenesCreateRoomVideoListAdapter.ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(arrayString[position]);
    }

    @Override
    public int getItemCount() {
        return arrayString.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void bind(String name) {
            binding.setVariable(BR.item, name);
            binding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        public void onItemClick(int position);
    }
}
