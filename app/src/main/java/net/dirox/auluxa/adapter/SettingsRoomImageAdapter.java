package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.RoomImageItem;

import java.util.ArrayList;

/**
 * Created by trungnq on 23/06/2017.
 */

public abstract class SettingsRoomImageAdapter extends RecyclerSwipeAdapter<SettingsRoomImageAdapter.ViewHolder> {

    private ArrayList<RoomImageItem> list;
    private int resource;
    //private final ViewBinderHelper binderHelper;

    private ItemActionCallback mItemActionCallback;
    private boolean isEditting = false;

    private boolean isLockEdittingText = true;

    public SettingsRoomImageAdapter(Context context, int resource, ArrayList<RoomImageItem> list) {
        this.list = list;
        this.resource = resource;
        //binderHelper = new ViewBinderHelper();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_room_image, parent, false);
        SettingsRoomImageAdapter.ViewHolder holder = new SettingsRoomImageAdapter.ViewHolder(v);

        holder.tvRoomName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isLockEdittingText) {
                    if (mItemActionCallback != null && !isEditting) {
                        if (list.size() > 0 && list.size() - 1 >= holder.getAdapterPosition()) {
                            isEditting = true;
                            RoomImageItem item = list.get(holder.getAdapterPosition());
                            item.roomName = holder.tvRoomName.getText().toString();
                            list.set(holder.getAdapterPosition(), item);
                            mItemActionCallback.onItemEditName(holder.getAdapterPosition(), getDataList().get(holder.getAdapterPosition()));
                            isEditting = false;
                        }
                    }
                }
            }
        });

        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemActionCallback != null) {
                    mItemActionCallback.onItemDeleted(holder.getAdapterPosition(), getDataList().get(holder.getAdapterPosition()));
                }

            }
        });

        holder.imgChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(holder.getAdapterPosition());
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.itemView.getContext().getApplicationContext();

        holder.tvRoomName.setText(list.get(position).roomName);
        holder.tvRoomName.setAllCaps(true);

        Glide.with(context.getApplicationContext())
                .load(list.get(position).roomImagePath)
                .crossFade()
                .into(holder.imgRoomImage);

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {

            @Override
            public void onOpen(SwipeLayout layout) {

            }
        });

        mItemManger.bindView(holder.itemView, holder.getAdapterPosition());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public abstract void onItemSelected(int position);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public EditText tvRoomName;
        public ImageView imgRoomImage;
        public ImageView imgChooseImage;
        public SwipeLayout swipeLayout;
        public View deleteView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRoomName = (EditText) itemView.findViewById(R.id.tvRoomName);
            imgChooseImage = (ImageView) itemView.findViewById(R.id.imgChooseImage);
            imgRoomImage = (ImageView) itemView.findViewById(R.id.imgRoomImage);
            deleteView = itemView.findViewById(R.id.delete_layout);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipeLayoutRoom);
        }
    }

    public void atDataAt(int position, RoomImageItem data) {
        if (list != null) {
            list.add(0, data);
            mItemManger.closeAllItems();
            notifyItemInserted(0);
        }
    }

    public void removeDataAt(int position) {
        if (list != null) {
            list.remove(position);
            notifyItemRemoved(position);
            mItemManger.closeAllItems();
            notifyDataSetChanged();
        }
    }

    public void setLockEdittingText(boolean lockEdittingText) {
        isLockEdittingText = lockEdittingText;
    }

    public void setItemActionCallback(ItemActionCallback itemActionCallback) {
        this.mItemActionCallback = itemActionCallback;
    }

    public ArrayList<RoomImageItem> getDataList() {
        return list;
    }

    public interface ItemActionCallback {
        public void onItemEditName(int position, RoomImageItem data);

        public void onItemDeleted(int position, RoomImageItem data);
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayoutRoom;
    }

}