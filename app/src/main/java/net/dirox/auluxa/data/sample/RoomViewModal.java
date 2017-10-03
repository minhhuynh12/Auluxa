package net.dirox.auluxa.data.sample;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.dirox.auluxa.BR;
import net.dirox.auluxa.R;
import net.dirox.auluxa.adapter.RecyclerBindingAdapter;
import net.dirox.auluxa.adapter.RecyclerConfiguration;
import net.dirox.auluxa.utils.drap_drop_sequence.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by an on 6/8/2017.
 */

public class RoomViewModal implements OnStartDragListener {
    private Context context;
    private RecyclerBindingAdapter.OnItemClickListener<RoomImageItem> mCallback;
    private ItemTouchHelper mItemTouchHelper;
    public final RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration();
    public RecyclerBindingAdapter<RoomImageItem> adapter = new RecyclerBindingAdapter<>(R.layout.layout_item_navigation_drawer, BR.item, new ArrayList<>(), this);

    public RoomViewModal(Context context, RecyclerBindingAdapter.OnItemClickListener<RoomImageItem> listener) {
        this.context = context;
        mCallback = listener;
        initRecycler();
    }

    public void setData(List<RoomImageItem> datas) {
        adapter.setItems(new ArrayList<>(datas));
    }

    public synchronized void setItemSelected(int lastSelected, int newSelect) {

        for (int i = 0; i < adapter.getItemCount(); i++) {
            if (i == newSelect) {
                adapter.getItem(i).isSelected = true;
            } else {
                adapter.getItem(i).isSelected = false;
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void initRecycler() {
        LinearLayoutManager layout = new LinearLayoutManager(context);
        recyclerConfiguration.setLayoutManager(layout);
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                //do something
            }
        });

        recyclerConfiguration.setAdapter(adapter);
        adapter.setOnItemClickListener(mCallback);
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
    }

    @BindingAdapter("app:resourceId")
    public static void loadImageResource(ImageView imageView, int resourceId) {
        Glide.with(imageView.getContext())
                .load(resourceId)
                .into(imageView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        if(mItemTouchHelper != null) {
            mItemTouchHelper.startDrag(viewHolder);
        }
    }

    public void setItemTouchHelper(ItemTouchHelper mItemTouchHelper) {
        this.mItemTouchHelper = mItemTouchHelper;
    }
}
