package net.dirox.auluxa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;

import java.util.ArrayList;
import java.util.List;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.ViewHolder> {

    List<String> f;
    Boolean isCode;

    public interface OnClickItem {
        public void onClick();
    }

    OnClickItem onClickItem;

    public CodeAdapter(List<String> list, boolean isCode, OnClickItem onClickItem) {
        f = list;
        this.isCode = isCode;
        this.onClickItem = onClickItem;
    }

    int select = -1;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settings_code, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvName.setText(f.get(position).toString());
        if (position == select)
            holder.cbCheck.setChecked(true);
        else holder.cbCheck.setChecked(false);
        if (!isCode)
            holder.tvCode.setVisibility(View.GONE);
        else holder.tvCode.setVisibility(View.VISIBLE);
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick();
            }
        });
        holder.cbCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick();
                if (select != position)
                    select = position;
                else select = -1;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return f.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCode;
        CheckBox cbCheck;
        RelativeLayout rlItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCode = (TextView) itemView.findViewById(R.id.tvCode);
            cbCheck = (CheckBox) itemView.findViewById(R.id.cbCheck);
            rlItem = (RelativeLayout) itemView.findViewById(R.id.rlItem);
        }
    }

    public void reloadList(List<String> list) {
        f = list;
        notifyDataSetChanged();
    }

    public void filter(String keyword, ArrayList<String> list) {
        ArrayList<String> arraylist = new ArrayList<>();
        keyword = keyword.toLowerCase();
        if (keyword.length() == 0) {
            arraylist.addAll(list);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toLowerCase().contains(keyword)) {
                    arraylist.add(list.get(i));
                }
            }
            reloadList(arraylist);
        }
        notifyDataSetChanged();
    }

    public int getSelect(){
        return select;
    }
}