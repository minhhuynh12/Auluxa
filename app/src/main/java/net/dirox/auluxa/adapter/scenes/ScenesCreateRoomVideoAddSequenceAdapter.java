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
import net.dirox.auluxa.data.sample.VideoSequenceItem;

import java.util.ArrayList;

/**
 * Created by an on 6/29/2017.
 */

public class ScenesCreateRoomVideoAddSequenceAdapter extends RecyclerView.Adapter<ScenesCreateRoomVideoAddSequenceAdapter.ViewHolder> {
    private static final int VIEW_TYPE_SEQUENCE_ITEM = 1;
    private static final int VIEW_TYPE_ADD_SEQUENCE = 2;

    private Context mContext;
    private LayoutInflater mInflater;
    private ItemClickListener mCallback;

    private ArrayList<VideoSequenceItem> arraySequence;

    public ScenesCreateRoomVideoAddSequenceAdapter(Context context, ArrayList<VideoSequenceItem> listSequence, ItemClickListener listener) {
        this.mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCallback = listener;
        this.arraySequence = listSequence;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SEQUENCE_ITEM) {
            view = mInflater.inflate(R.layout.layout_item_scenes_room_sequence, parent, false);
        } else {
            view = mInflater.inflate(R.layout.layout_item_scenes_room_add_sequence, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (getItemViewType(position) == VIEW_TYPE_SEQUENCE_ITEM) {
            holder.getBinding().setVariable(BR.item, arraySequence.get(position));
        }

        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItemViewType(position) == VIEW_TYPE_SEQUENCE_ITEM) {
                    mCallback.onItemClick(position);
                } else {
                    mCallback.onAddSequenceClicked();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraySequence.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return VIEW_TYPE_ADD_SEQUENCE;
        }
        return VIEW_TYPE_SEQUENCE_ITEM;
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

        void onItemClick(int position);

        void onAddSequenceClicked();
    }
}
