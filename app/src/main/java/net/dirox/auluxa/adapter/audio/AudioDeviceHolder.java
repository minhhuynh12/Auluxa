package net.dirox.auluxa.adapter.audio;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import net.dirox.auluxa.BR;
import net.dirox.auluxa.R;
import net.dirox.auluxa.data.sample.AudioDeviceItem;
import net.dirox.auluxa.widget.QuickAction.ActionItem;
import net.dirox.auluxa.widget.QuickAction.QuickAction;

/**
 * Created by an on 6/27/2017.
 */

public class AudioDeviceHolder extends ChildViewHolder {

    ViewDataBinding binding;
    ImageView btnMore,btnMoreBot,btnMoreBot2,imageView8;
    QuickAction quickAction;
    LinearLayout llItem;
    TextView textView3;
    RelativeLayout rlPin;
    int addedXPos;

    public AudioDeviceHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        btnMore = (ImageView) binding.getRoot().findViewById(R.id.btnMore);
        btnMoreBot = (ImageView) binding.getRoot().findViewById(R.id.btnMoreBot);
        btnMoreBot2 = (ImageView) binding.getRoot().findViewById(R.id.btnMoreBot2);
        llItem = (LinearLayout) binding.getRoot().findViewById(R.id.llItem);
        rlPin = (RelativeLayout) binding.getRoot().findViewById(R.id.rlPin);
        textView3 = (TextView) binding.getRoot().findViewById(R.id.textView3);
        imageView8 = (ImageView) binding.getRoot().findViewById(R.id.imageView8);

        quickAction = new QuickAction(itemView.getContext(), 2);
        quickAction.addActionItem(new ActionItem(1, "Settings", null));
        quickAction.addActionItem(new ActionItem(2, "Create group", null));

        addedXPos = (int) itemView.getContext().getResources().getDimension(R.dimen.distance_normal);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quickAction.show(v, addedXPos);
            }
        });
    }

    public void bind(AudioDeviceItem audioItem) {
        binding.setVariable(BR.item, audioItem);
    }
}
