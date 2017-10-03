package net.dirox.auluxa.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.dirox.auluxa.R;
import net.dirox.auluxa.video.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    public interface OnClickItemUserListener {
        public void inClick(int pos);

        public void inClickAddItem(int pos);
    }

    OnClickItemUserListener OnClickItemUserListener;
    ArrayList<User> f = new ArrayList<>();
    Context context;
    int select = -1;

    public UserAdapter(ArrayList<User> list, OnClickItemUserListener _onClickItemListener) {
        OnClickItemUserListener = _onClickItemListener;
        f = list;
        f.add(new User("ADD ACCOUNT", false, "", "", false, false, false));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvNameButton.setText(f.get(position).name);
        if (position == (f.size() - 1)) {
            holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.auluxaGreen));
            holder.ivArrow.setImageResource(R.drawable.add_icon);
            holder.tvAdmin.setVisibility(View.GONE);
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select = position;
                    OnClickItemUserListener.inClickAddItem(position);
                }
            });
        } else {
            if (f.get(position).isAdmin)
                holder.tvAdmin.setVisibility(View.VISIBLE);
            else holder.tvAdmin.setVisibility(View.GONE);
            holder.tvNameButton.setTextColor(ContextCompat.getColor(context, R.color.BgDarker));
            holder.ivArrow.setImageResource(R.drawable.fragment_video_icon_arrow);
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select = position;
                    OnClickItemUserListener.inClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return f.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameButton, tvAdmin;
        ImageView ivArrow;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNameButton = (TextView) itemView.findViewById(R.id.tvName);
            tvAdmin = (TextView) itemView.findViewById(R.id.tvAdmin);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lnItem);
            ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
        }

    }

    public void updateUser(String name,int pos, boolean isAdmin, String email, String tvPasswordContent, boolean llLivingRoom, boolean llDiningRoom, boolean llGuestBedroom) {
        f.set(pos, new User(name, isAdmin, email, tvPasswordContent, llLivingRoom, llDiningRoom, llGuestBedroom));
        notifyDataSetChanged();
    }

    public void addUser(String name, boolean isAdmin, String email, String tvPasswordContent, boolean llLivingRoom, boolean llDiningRoom, boolean llGuestBedroom) {
        f.remove(f.size() - 1);
        f.add(new User(name, isAdmin, email, tvPasswordContent, llLivingRoom, llDiningRoom, llGuestBedroom));
        f.add(new User("ADD ACCOUNT", false, "", "", false, false, false));
        notifyDataSetChanged();
    }

    public void removeItem(int pos) {
        f.remove(pos);
        notifyDataSetChanged();
    }

}