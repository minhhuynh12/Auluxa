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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by an on 6/20/2017.
 */

public abstract class SecurityMainAdapter extends RecyclerView.Adapter<SecurityMainAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mainItems;

    public SecurityMainAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mainItems = new ArrayList<>(Arrays.asList(mContext.getResources().getStringArray(R.array.menu_securities_main_items)));
    }

    public abstract void onItemSelected(int position, String stringValue);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_settings, parent, false);
        return new SecurityMainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mainItems.get(position));

        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(position, mainItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainItems.size();
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
            binding.setVariable(BR.item, name);
            binding.executePendingBindings();
        }
    }
}
