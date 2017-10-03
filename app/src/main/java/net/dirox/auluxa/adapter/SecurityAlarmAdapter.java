package net.dirox.auluxa.adapter;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import net.dirox.auluxa.BR;
import net.dirox.auluxa.R;
import net.dirox.auluxa.data.GeneratorSampleData;
import net.dirox.auluxa.data.sample.SecurityItem;

/**
 * Created by an on 6/20/2017.
 */

public class SecurityAlarmAdapter extends RecyclerView.Adapter<SecurityAlarmAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;

    public SecurityAlarmAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_security_alarm, parent, false);
        return new SecurityAlarmAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SecurityItem.Alarm alarm = GeneratorSampleData.securityItem.getAlarmKey(position);
        boolean isOn = GeneratorSampleData.securityItem.hashMapAlarm.get(alarm);

        holder.bind(alarm.alarm, isOn);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_security_video_password);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText etPassword = (EditText) dialog.findViewById(R.id.etPassword);

                dialog.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etPassword.getText().length() > 0) {
                            dialog.dismiss();
                            GeneratorSampleData.securityItem.toggleAlarmStatus(alarm);
                            notifyItemChanged(position);
                        }
                    }
                });

                dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return GeneratorSampleData.securityItem.hashMapAlarm.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            imageView = (ImageView) binding.getRoot().findViewById(R.id.imageView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void bind(String name, boolean isOn) {
            imageView.setSelected(isOn);
            binding.setVariable(BR.name, name);
            binding.setVariable(BR.isOn, isOn);
            binding.executePendingBindings();
        }
    }
}
