package net.dirox.auluxa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.BluetoothItems;

import java.util.ArrayList;

/**
 * Created by MyPC on 31/07/2017.
 */

public abstract class BluetoothAdapter extends RecyclerView.Adapter<BluetoothAdapter.ViewHolder> {
    private Context mContext;
    ArrayList<BluetoothItems> bluetoothItemseslist;
    public ArrayList<BluetoothItems> get(){
        return bluetoothItemseslist;
    }

    public BluetoothAdapter(ArrayList<BluetoothItems> bluetoothItemses){
        this.bluetoothItemseslist = bluetoothItemses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_security_bluetooth_key_items, parent , false);
        mContext = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imgBluetooth.setImageResource(bluetoothItemseslist.get(position).getImage());
        holder.txtFirstBluetooth.setText(bluetoothItemseslist.get(position).getName());
        holder.txtSecondBluetooth.setText(bluetoothItemseslist.get(position).getDescription());
        if(holder.txtSecondBluetooth.getText().equals("ACCEPTED")){
            holder.txtSecondBluetooth.setTextColor(Color.parseColor("#18bd9b"));
        }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemSelected(position);
                }
            });


        if(bluetoothItemseslist.get(position).getDescription().equals("")){
            holder.txtSecondBluetooth.setVisibility(View.GONE);
        }
        int lastPosition = bluetoothItemseslist.size() - 1;
        if(position == lastPosition){
            holder.txtFirstBluetooth.setTextColor(Color.parseColor("#18bd9b"));
        }
    }

    @Override
    public int getItemCount() {
        return bluetoothItemseslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBluetooth;
        TextView txtFirstBluetooth;
        TextView txtSecondBluetooth;
        public ViewHolder(View itemView) {
            super(itemView);
            imgBluetooth =  (ImageView) itemView.findViewById(R.id.imgBluetooth);
            txtFirstBluetooth = (TextView) itemView.findViewById(R.id.txtFirstBluetooth);
            txtSecondBluetooth = (TextView) itemView.findViewById(R.id.txtSecondBluetooth);
        }
    }
    public abstract void onItemSelected(int position);
}
