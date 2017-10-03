package net.dirox.auluxa.widget;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandCollapseController;
import com.thoughtbot.expandablerecyclerview.listeners.ExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableList;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import net.dirox.auluxa.adapter.audio.GroupAudioDeviceHolder;
import net.dirox.auluxa.data.sample.AudioGroupDeviceItem;

import java.util.List;

/**
 * Created by an on 6/27/2017.
 */

public abstract class RecyclerViewExpandableAdapter<GVH extends GroupViewHolder, CVH extends ChildViewHolder> extends RecyclerView.Adapter implements ExpandCollapseListener, OnGroupClickListener {

    private static final String EXPAND_STATE_MAP = "recyclerview_expandable_adapter_expand_state_map";

    protected ExpandableList expandableList;
    private ExpandCollapseController expandCollapseController;

    private OnGroupClickListener groupClickListener;
    private GroupExpandCollapseListener expandCollapseListener;

    public RecyclerViewExpandableAdapter(List<AudioGroupDeviceItem> groups) {
        this.expandableList = new ExpandableList(groups);
        this.expandCollapseController = new ExpandCollapseController(expandableList, this);
    }

    @Override
    public int getItemCount() {
        return expandableList.getVisibleItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        int type = expandableList.getUnflattenedPosition(position).type;
        int visiblePosition = expandableList.getUnflattenedPosition(position).groupPos;
        if (type == ExpandableListPosition.GROUP) {
            if (((List<AudioGroupDeviceItem>) expandableList.groups).get(visiblePosition).getItemCount() == 1) {
                return AudioGroupDeviceItem.GROUP_ITEM_DETAIL;
            }
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case AudioGroupDeviceItem.CHILD:
                CVH cvh = onCreateChildViewHolder(parent, viewType);
                return cvh;
            case AudioGroupDeviceItem.GROUP_TITLE:
                GVH gvh = onCreateGroupViewHolder(parent, viewType);

                if (gvh instanceof GroupAudioDeviceHolder) {
                    gvh.setOnGroupClickListener(this);
                } else {
                    gvh.setOnGroupClickListener(this);
                }

                return gvh;

            case AudioGroupDeviceItem.GROUP_ITEM_DETAIL:
                CVH gvhd = onCreateChildViewHolder(parent, viewType);
                return gvhd;
            default:
                throw new IllegalArgumentException("viewType is not valid");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(position);
        ExpandableGroup group = expandableList.getExpandableGroup(listPos);
        int groupPosition = expandableList.getFlattenedGroupIndex(listPos);

        switch (getItemViewType(position)) {
            case AudioGroupDeviceItem.GROUP_TITLE:
                onBindGroupViewHolder((GVH) holder, groupPosition, group);
                break;

            case AudioGroupDeviceItem.CHILD:
            case AudioGroupDeviceItem.GROUP_ITEM_DETAIL:
                onBindChildViewHolder((CVH) holder, groupPosition, group, getItemViewType(position), listPos.childPos);
                break;
        }
    }

    @Override
    public void onGroupExpanded(int positionStart, int itemCount) {
        // only insert if there items to insert
        if (itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
            int groupIndex = expandableList.getUnflattenedPosition(positionStart).groupPos;
            if (expandCollapseListener != null) {
                expandCollapseListener.onGroupExpanded(getGroups().get(groupIndex));
            }
            onGroupExpanded(groupIndex);
        }
    }

    @Override
    public void onGroupCollapsed(int positionStart, int itemCount) {
        // only remote if there items to remove
        if (itemCount > 0) {
            notifyItemRangeRemoved(positionStart, itemCount);
            if (expandCollapseListener != null) {
                //minus one to return the position of the header, not first child
                int groupIndex = expandableList.getUnflattenedPosition(positionStart - 1).groupPos;
                expandCollapseListener.onGroupCollapsed(getGroups().get(groupIndex));
            }
        }
    }

    @Override
    public boolean onGroupClick(int flatPos) {
        if (groupClickListener != null) {
            groupClickListener.onGroupClick(flatPos);
        }
        return expandCollapseController.toggleGroup(flatPos);
    }

    public boolean toggleGroup(int flatPos) {
        return expandCollapseController.toggleGroup(flatPos);
    }

    public boolean toggleGroup(ExpandableGroup group) {
        return expandCollapseController.toggleGroup(group);
    }

    public boolean isGroupExpanded(int flatPos) {
        return expandCollapseController.isGroupExpanded(flatPos);
    }

    public boolean isGroupExpanded(ExpandableGroup group) {
        return expandCollapseController.isGroupExpanded(group);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBooleanArray(EXPAND_STATE_MAP, expandableList.expandedGroupIndexes);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null || !savedInstanceState.containsKey(EXPAND_STATE_MAP)) {
            return;
        }
        expandableList.expandedGroupIndexes = savedInstanceState.getBooleanArray(EXPAND_STATE_MAP);
        notifyDataSetChanged();
    }

    public void setOnGroupClickListener(OnGroupClickListener listener) {
        groupClickListener = listener;
    }

    public void setOnGroupExpandCollapseListener(GroupExpandCollapseListener listener) {
        expandCollapseListener = listener;
    }

    public List<? extends ExpandableGroup> getGroups() {
        return expandableList.groups;
    }

    public abstract GVH onCreateGroupViewHolder(ViewGroup parent, int viewType);

    public abstract CVH onCreateChildViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindChildViewHolder(CVH holder, int groupPosition, ExpandableGroup group, int viewType, int childIndex);

    public abstract void onBindGroupViewHolder(GVH holder, int groupPosition, ExpandableGroup group);

    public abstract void onGroupExpanded(int groupIndex);
}

