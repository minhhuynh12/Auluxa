package net.dirox.auluxa.adapter.audio;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import net.dirox.auluxa.R;
import net.dirox.auluxa.widget.QuickAction.ActionItem;
import net.dirox.auluxa.widget.QuickAction.QuickAction;

/**
 * Created by an on 6/27/2017.
 */

public class GroupAudioDeviceHolder extends GroupViewHolder {

    public ConstraintLayout groupToggle;
    TextView txtName;
    TextView txtNumber;
    ImageView btnMore;
    QuickAction quickAction;
    int addedXPos;

    public GroupAudioDeviceHolder(View itemView) {
        super(itemView);
        groupToggle = (ConstraintLayout) itemView.findViewById(R.id.groupToggle);
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtNumber = (TextView) itemView.findViewById(R.id.txtNumber);
        btnMore = (ImageView) itemView.findViewById(R.id.btnMore);

        quickAction = new QuickAction(itemView.getContext(), 2);
        quickAction.addActionItem(new ActionItem(1, "EDIT GROUP", null));
        quickAction.addActionItem(new ActionItem(2, "DELETE GROUP", null));
        addedXPos = (int) itemView.getContext().getResources().getDimension(R.dimen.distance_normal);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quickAction.show(v, addedXPos);
            }
        });
    }

    public void bind(String groupName, int numberChild) {
        txtName.setText(groupName);
        txtNumber.setText("" + numberChild);
    }
}
