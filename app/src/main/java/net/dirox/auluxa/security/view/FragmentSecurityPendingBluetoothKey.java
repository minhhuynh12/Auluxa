package net.dirox.auluxa.security.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.BluetoothItems;
import net.dirox.auluxa.databinding.FragmentSecurityPendingBluetoothKeyBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;


import static net.dirox.auluxa.security.view.FragmentSecurityLock.bluetoothItemslist;

public class FragmentSecurityPendingBluetoothKey extends BaseFragment {
    private FragmentSecurityPendingBluetoothKeyBinding mBinding;
    private FragmentInteraction mInteraction;
    private int position;

    BluetoothItems iTems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_pending_bluetooth_key, container, false);
        Bundle removeBundle = new Bundle();
        removeBundle.putInt("backRemove", 3);


        Bundle bundle = getArguments();
        position = bundle.getInt("positionBluetooth");
        if (bundle != null) {
            iTems = (BluetoothItems) bundle.getSerializable("Object");
            mBinding.textViewAPContentName.setText(iTems.getName());
            mBinding.textViewContentTimeZone.setText(iTems.getTimeZone());
            mBinding.textViewStartDate.setText(iTems.getStartDate());
            mBinding.textViewStartTime.setText(iTems.getStartTime());

            mBinding.textViewEndDate.setText(iTems.getEndDate());
            mBinding.textViewEndTime.setText(iTems.getEndTime());
            mBinding.textViewContentPin.setText((iTems.getPin()));

            if (iTems.getDescription().equals("PENDING")) {
                mBinding.textViewAPContentStatus.setImageResource(R.drawable.pending);
            } else {
                mBinding.textViewAPContentStatus.setImageResource(R.drawable.accept);
                mBinding.btnShareBluetooth.setVisibility(View.GONE);
                mBinding.headerDetailBluetooth.setText("ACCEPTED BLUETOOTH");
            }
        }
        mBinding.btnShareBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent myIntentShare = new Intent(Intent.ACTION_SEND);
//                myIntentShare.setType("text");
//                String sharebody = "";
//                myIntentShare.putExtra(Intent.EXTRA_SUBJECT, sharebody);
//                myIntentShare.putExtra(Intent.EXTRA_TEXT, sharebody);
//                startActivity(Intent.createChooser(myIntentShare, "Share"));

                Intent myIntentShare = new Intent(Intent.ACTION_SEND);
                myIntentShare.setType("text/plain");
                String shareBody ="";
                String shareSub ="";
                myIntentShare.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                myIntentShare.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntentShare,"Share"));
            }
        });
        mBinding.btnRemoveBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_security_pending_bluetooth_key);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.findViewById(R.id.btnRemove).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bluetoothItemslist.remove(position);

                        Prefs.saveList(getContext(), Constant.ARRAY_BLUETOOTH, bluetoothItemslist);
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK, removeBundle, Enumes.Direction.RIGHT_IN);
                        dialog.dismiss();
                    }
                });

                dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK, removeBundle, Enumes.Direction.RIGHT_IN);
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
