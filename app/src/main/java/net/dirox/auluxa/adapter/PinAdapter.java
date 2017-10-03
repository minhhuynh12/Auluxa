package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.PinItem;

import java.util.ArrayList;

/**
 * Created by MyPC on 21/07/2017.
 */

public abstract class PinAdapter extends RecyclerView.Adapter<PinAdapter.ViewHolder> {
     Context context;
    ArrayList<PinItem> pinItemslist;
    private LayoutInflater mInflater;
    public PinAdapter(ArrayList<PinItem> pinItems){
        this.pinItemslist = pinItems;

    }

    public PinAdapter(Context context) {
        context = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = mInflater.inflate(R.layout.fragment_security_pin_list, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_security_pin_list, parent , false);
        context = parent.getContext();


        return new ViewHolder(view);
    }
    public abstract void onItemSelected(int position);
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView2Pin.setImageResource(pinItemslist.get(position).getImg());
        holder.textViewNamePin.setText(pinItemslist.get(position).getTxtFirst());
        holder.textViewDescribePin.setText(pinItemslist.get(position).getTxtSecond());
        Log.e("--------", pinItemslist.get(position).getTxtFirst().toString());
        if (pinItemslist.get(position).getTxtSecond().equals("")) {
            holder.textViewDescribePin.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemSelected(position);


            }
        });
    }

    @Override
    public int getItemCount() {
        return pinItemslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView2Pin;
        TextView textViewNamePin;
        TextView textViewDescribePin;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView2Pin = (ImageView) itemView.findViewById(R.id.imageView2Pin);
            textViewNamePin = (TextView) itemView.findViewById(R.id.textViewNamePin);
            textViewDescribePin = (TextView) itemView.findViewById(R.id.textViewDescribePin);
        }
    }

}
