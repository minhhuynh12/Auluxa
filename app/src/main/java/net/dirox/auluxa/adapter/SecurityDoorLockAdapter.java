package net.dirox.auluxa.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.BR;
import net.dirox.auluxa.R;
import net.dirox.auluxa.data.GeneratorSampleData;
import net.dirox.auluxa.data.sample.SecurityItem;

//import net.dirox.auluxa.BR;

/**
 * Created by an on 6/20/2017.
 */

public abstract class SecurityDoorLockAdapter extends RecyclerView.Adapter<SecurityDoorLockAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    public boolean showDialog = false;
    public int select = -1;

    public SecurityDoorLockAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_security_door_lock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SecurityItem.Door door = GeneratorSampleData.securityItem.getDoorKey(position);
        boolean isOn = GeneratorSampleData.securityItem.hashMapDoor.get(door);

        holder.bind(door.door, isOn);

        if(select == -1){
            holder.itemViewDoorLock.setImageResource(R.drawable.ic_door_lock_selector);
        }else{
            holder.itemViewDoorLock.setImageResource(R.drawable.icon_lock_changed);
        }
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemSelected(holder.getAdapterPosition());
            }
        });
        holder.itemViewDoorLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return GeneratorSampleData.securityItem.hashMapDoor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        private TextView txtDoorlock;
        private ImageView itemViewDoorLock;
        private View rootLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            txtDoorlock = (TextView) itemView.findViewById(R.id.txtDoorlock);
            rootLayout = itemView.findViewById(R.id.rootLayout);
            itemViewDoorLock = (ImageView) itemView.findViewById(R.id.imageViewDoorLock);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void bind(String name, boolean isOn) {
            txtDoorlock.setText(name);
            binding.setVariable(BR.name, name);
            binding.setVariable(BR.isOn, isOn);
            binding.executePendingBindings();

        }

    }

    public void setSelected(int select){
        this.select = select;
        notifyItemChanged(select);
    }

    public abstract void onItemSelected(int position);

    public abstract void showPopUp(int position);

}
