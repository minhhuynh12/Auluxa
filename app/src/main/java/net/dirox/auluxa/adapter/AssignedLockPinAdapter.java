package net.dirox.auluxa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.AssignedLockPinItem;

import java.util.ArrayList;

/**
 * Created by DoanThiPhuongHuyen on 26/07/2017.
 */

public abstract class AssignedLockPinAdapter extends RecyclerView.Adapter<AssignedLockPinAdapter.ViewHolder> {
    ArrayList<AssignedLockPinItem> assignedLockPinItemArrayList;
    Context context;
    boolean flag;

    public ArrayList<AssignedLockPinItem> getAssignedLockPinItemArrayList() {
        return assignedLockPinItemArrayList;
    }

    public void setAssignedLockPinItemArrayList(ArrayList<AssignedLockPinItem> assignedLockPinItemArrayList) {
        this.assignedLockPinItemArrayList = assignedLockPinItemArrayList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public AssignedLockPinAdapter(ArrayList<AssignedLockPinItem> assignedLockPinItemArrayList, Context context, boolean flag) {
        this.assignedLockPinItemArrayList = assignedLockPinItemArrayList;
        this.context = context;
        this.flag = flag;
    }
    //final Button  btnShowPasswordPin = (Button) findViewById(R.id.btnShowPasswordPin);

    public AssignedLockPinAdapter(ArrayList<AssignedLockPinItem> assignedLockPinItemArrayList, Context context) {
        this.assignedLockPinItemArrayList = assignedLockPinItemArrayList;
        this.context = context;
    }
    public abstract void onItemSelected(int position);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.assigned_lock_pin_item, parent, false);
       // View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.imamgeViewItemPin.setImageResource(assignedLockPinItemArrayList.get(position).getImage());
        holder.txtName.setText(assignedLockPinItemArrayList.get(position).getName());
        holder.txtDescription.setText(assignedLockPinItemArrayList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemSelected(position);


            }
        });


        if(flag==true ) {

            holder.txtDescription.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        }
        else if(flag ==false)
        {
            holder.txtDescription.setTransformationMethod(PasswordTransformationMethod.getInstance());


        }
        if (assignedLockPinItemArrayList.get(position).getDescription().equals("")) {
           holder.txtName.setTextColor(Color.parseColor("#18bd9b"));
            holder.txtDescription.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return assignedLockPinItemArrayList.size();
    }
    public void atDataAt(int position, AssignedLockPinItem data) {
        if (assignedLockPinItemArrayList != null) {
            assignedLockPinItemArrayList.add(0, data);
           // mItemManger.closeAllItems();
            notifyItemInserted(0);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtDescription;
        ImageView imamgeViewItemPin;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.textViewNameAssignedLockPin);
            txtDescription = (TextView) itemView.findViewById(R.id.textViewPinAssignedLockPin);
            imamgeViewItemPin =(ImageView) itemView.findViewById(R.id.imageViewAssignedLockPinItem);

        }


    }


}
