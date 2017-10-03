package net.dirox.auluxa.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.BR;
import net.dirox.auluxa.R;
import net.dirox.auluxa.data.GeneratorSampleData;
import net.dirox.auluxa.data.sample.SecurityItem;

/**
 * Created by an on 6/20/2017.
 */

public abstract class SecuritySurvellianceAdapter extends RecyclerView.Adapter<SecuritySurvellianceAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;

    public SecuritySurvellianceAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public abstract void onSelectItem(int position, String name, int resourceId);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_security_survelliance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SecurityItem.CameraItem cameraItem = GeneratorSampleData.securityItem.arrayListCamera.get(position);
        holder.bind(cameraItem.name);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectItem(position, cameraItem.name, cameraItem.resourceId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return GeneratorSampleData.securityItem.arrayListCamera.size();
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

            binding.setVariable(BR.name, name);
            binding.executePendingBindings();
        }
    }
}
