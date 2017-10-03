package net.dirox.auluxa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.SequenceItem;
import net.dirox.auluxa.utils.drap_drop_sequence.ItemTouchHelperAdapter;
import net.dirox.auluxa.utils.drap_drop_sequence.ItemTouchHelperViewHolder;
import net.dirox.auluxa.utils.drap_drop_sequence.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by MyPC on 29/06/2017.
 */

public class SequenceAdapter extends RecyclerView.Adapter<SequenceAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    Context context;
    ArrayList<SequenceItem> sequenceItemArrayList;
    public boolean editable = false;
    public int temp;
    private int resource;

    private final OnStartDragListener mDragStartListener;

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(sequenceItemArrayList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        sequenceItemArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public SequenceAdapter(int resource, ArrayList<SequenceItem> sequenceItems, OnStartDragListener dragStartListener) {
        this.sequenceItemArrayList = sequenceItems;
        this.resource = resource;
        mDragStartListener = dragStartListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_scenes_create_sequence_list_items, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return sequenceItemArrayList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textViewSequenceFirst.setText(sequenceItemArrayList.get(position).getSequenceFirst());
        holder.textViewSequenceSecond.setText(sequenceItemArrayList.get(position).getSequenceSecond());
        holder.textViewSequenceSetDelay.setText(sequenceItemArrayList.get(position).getSequenceSetDelay());

        temp = Integer.parseInt(holder.textViewNumberText.getText().toString());

        if (editable) {
            holder.imageSequence.setImageResource(R.drawable.menu_green);
            holder.imageMinus.setImageResource(R.drawable.minus);
            holder.imagePlus.setImageResource(R.drawable.plus);
            holder.textViewNumberText.setTextColor(Color.parseColor("#18bd9b"));
            holder.textViewSequenceSetDelay.setTextColor(Color.parseColor("#18bd9b"));
            holder.relativeSecond.setAlpha(1f);
            holder.imageSequence.setAlpha(1f);

            holder.imagePlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout parent = (RelativeLayout) holder.imagePlus.getParent();
                    TextView textView = (TextView) parent.getChildAt(1);
                    String txt = textView.getText().toString();
                    int num = Integer.parseInt(txt);
                    num++;
                    textView.setText(String.valueOf(num));
                }
            });

            holder.imageMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout parent = (RelativeLayout) holder.imagePlus.getParent();
                    TextView textView = (TextView) parent.getChildAt(1);
                    String txt = textView.getText().toString();
                    int num = Integer.parseInt(txt);
                    if (num > 0) num--;
                    textView.setText(String.valueOf(num));
                }
            });

            // Start a drag whenever the handle view it touched
            holder.imageSequence.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mDragStartListener.onStartDrag(holder);
                    }
                    return false;
                }
            });

        } else {
            holder.imageSequence.setImageResource(R.drawable.menu_gray);
            holder.imageMinus.setImageResource(R.drawable.grey_minus);
            holder.imagePlus.setImageResource(R.drawable.grey_plus);
            holder.textViewNumberText.setTextColor(Color.parseColor("#B0000000"));
            holder.textViewSequenceSetDelay.setTextColor(Color.parseColor("#B0000000"));
            holder.relativeSecond.setAlpha(0.5f);
            holder.imageSequence.setAlpha(0.5f);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public TextView textViewSequenceFirst;
        public TextView textViewSequenceSecond;
        public TextView textViewSequenceSetDelay;
        public TextView textViewNumberText;
        public ImageView imageSequence;
        public ImageView imagePlus;
        public ImageView imageMinus;
        public RelativeLayout relativeLayout;
        public RelativeLayout relativeSecond;

        public ViewHolder(View itemView) {
            super(itemView);
            imageSequence = (ImageView) itemView.findViewById(R.id.imageSequence);
            imagePlus = (ImageView) itemView.findViewById(R.id.imagePlus);
            imageMinus = (ImageView) itemView.findViewById(R.id.imageMinus);
            textViewSequenceFirst = (TextView) itemView.findViewById(R.id.textViewSequenceFirst);
            textViewSequenceSecond = (TextView) itemView.findViewById(R.id.textViewSequenceSecond);
            textViewSequenceSetDelay = (TextView) itemView.findViewById(R.id.textViewSequenceSetDelay);
            textViewNumberText = (TextView) itemView.findViewById(R.id.textViewNumberText);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.lnItem);
            relativeSecond = (RelativeLayout) itemView.findViewById(R.id.relativeSecond);
        }

        @Override
        public void onItemSelected() {

        }

    }

}
