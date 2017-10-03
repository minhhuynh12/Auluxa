package net.dirox.auluxa.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.ShadesItem;
import net.dirox.auluxa.widget.IconButton;
import net.dirox.auluxa.widget.ShadesDisplay;

import java.util.ArrayList;

/**
 * Created by an on 6/13/2017.
 */

public class RoomCentreShadesAdapter extends RecyclerView.Adapter<RoomCentreShadesAdapter.BindingHolder> implements Runnable {

    private Context mContext;
    private LayoutInflater mInflater;

    private int variableId;
    private ArrayList<ShadesItem> mShadesItem;

    public RoomCentreShadesAdapter(Context context, int variableId, ArrayList<ShadesItem> items) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mShadesItem = items;
        this.variableId = variableId;
    }

    public synchronized void setAllShadesState(boolean isOpen) {
        for (int i = 0; i < mShadesItem.size(); i++) {
            //changeShadeValue(mShadesItem.get(i), isOpen);
            if (isOpen == true) {
                mShadesItem.get(i).shadesPercent = 0;
            } else {
                mShadesItem.get(i).shadesPercent = 100;
            }
        }
        notifyDataSetChanged();
    }

    private void changeShadeValue(ShadesItem item, boolean isOpen) {
        if (isOpen == true) {
            if (item.isOpenable()) {
                item.open();
            }
        } else {
            if (item.isCloseable()) {
                item.close();
            }
        }
    }

    @Override
    public void run() {

    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_shade, parent, false);
        return new RoomCentreShadesAdapter.BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ShadesItem thisShades = mShadesItem.get(position);
        holder.getBinding().setVariable(variableId, thisShades);

        final IconButton btnOpen = (IconButton) holder.getBinding().getRoot().findViewById(R.id.ib_shadesUp);
        final IconButton btnClose = (IconButton) holder.getBinding().getRoot().findViewById(R.id.ib_shadesDown);
        ShadesDisplay shadesDisplay = (ShadesDisplay) holder.getBinding().getRoot().findViewById(R.id.shadesDisplay);
        shadesDisplay.setLvl(thisShades.shadesPercent);
        shadesDisplay.setOrientation(thisShades.isHorizontalShade);

        final AlphaAnimation blinkingAnim = new AlphaAnimation(1f, 0.3f);
        blinkingAnim.setDuration(500);
        blinkingAnim.setRepeatMode(Animation.REVERSE);
        blinkingAnim.setRepeatCount(Animation.INFINITE);

        View.OnClickListener openCloseListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isOpen = view.getId() == btnOpen.getId() ? true : false;
                if (isOpen == true) {
                    if (thisShades.isOpenable()) {
                        thisShades.open();
                        notifyItemChanged(position);
                    }
                } else {
                    if (thisShades.isCloseable()) {
                        thisShades.close();
                        notifyItemChanged(position);
                    }
                }
                /*if (thisShades.isOn()) {
                    stopAnimation();
                } else {
                    //thisShades.CONTROL(view.getId() == btnOpen.getId() ? 0 : 99);
                    startAnimation(view);
                }*/
            }

            private void startAnimation(View view) {
                thisShades.setOn(true);

                view.startAnimation(blinkingAnim);
                shadesDisplay.startAnim(view.getId() == R.id.ib_shadesDown);
            }

            private void stopAnimation() {
                thisShades.setOn(false);

                btnOpen.clearAnimation();
                btnClose.clearAnimation();
            }
        };

        btnClose.setOnClickListener(openCloseListener);
        btnOpen.setOnClickListener(openCloseListener);
        btnOpen.setOn(thisShades.isOpenable());
        btnClose.setOn(thisShades.isCloseable());
    }

    @Override
    public int getItemCount() {
        return mShadesItem.size();
    }


    static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void bind(){

        }
    }
}
