package net.dirox.auluxa.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.utils.drap_drop_sequence.ItemTouchHelperAdapter;
import net.dirox.auluxa.utils.drap_drop_sequence.ItemTouchHelperViewHolder;
import net.dirox.auluxa.utils.drap_drop_sequence.OnStartDragListener;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by an on 6/8/2017.
 */

public class RecyclerBindingAdapter<T>
        extends RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder> implements ItemTouchHelperAdapter {

    private int holderLayout, variableId;
    private AbstractList<T> items = new ArrayList<>();
    private OnItemClickListener<T> onItemClickListener;
    private final OnStartDragListener mDragStartListener;
    private GestureDetector gestureDetector;
    private boolean mayBeOnClick = false;
    private float mLastY;

    public RecyclerBindingAdapter(int holderLayout, int variableId, AbstractList<T> items, OnStartDragListener dragStartListener) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
        mDragStartListener = dragStartListener;
    }

    @Override
    public RecyclerBindingAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(holderLayout, parent, false);
        BindingHolder holder = new BindingHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerBindingAdapter.BindingHolder holder, int position) {
        final T item = items.get(position);
        int positionT = holder.getAdapterPosition();
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(positionT, items.get(position));
        });

        holder.getBinding().setVariable(variableId, item);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setItems(AbstractList<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T item);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        @Override
        public void onItemSelected() {

        }
    }




}
