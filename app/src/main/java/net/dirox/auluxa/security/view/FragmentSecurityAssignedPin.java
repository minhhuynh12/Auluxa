package net.dirox.auluxa.security.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.AssignedLockPinItem;
import net.dirox.auluxa.databinding.FragmentSecurityAssignedPinBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import static net.dirox.auluxa.security.view.FragmentSecurityAssignedLockPin.arrayList;


/**
 * Created by DoanThiPhuongHuyen on 27/07/2017.
 */

public class FragmentSecurityAssignedPin extends BaseFragment {

    private FragmentSecurityAssignedPinBinding mBinding;
    private FragmentInteraction mInteraction;
    AssignedLockPinItem item;
    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_assigned_pin, container, false);
        // Received data from Assigned Lock Pin
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            position = bundle.getInt("Position");
             item = (AssignedLockPinItem )bundle.getSerializable("Object");
            mBinding.textViewAPContentName.setText(item.getName());
            mBinding.editTextNamePin.setText(item.getName());
            mBinding.textViewAPContentStatus.setText(item.getStatus());
            mBinding.textViewContentDuration.setText(item.getDuration());
            mBinding.textViewContentPin.setText(item.getDescription());
            if(item.getStatus().equals("IN USE"))
            {
                mBinding.textViewAPContentStatus.setTextColor(Color.parseColor("#6cbe74"));
                mBinding.textViewAPContentStatus.setBackgroundResource(R.drawable.custom_content_pin_green);
            }else if(item.getStatus().equals("USED & EXPIRED")){
                mBinding.textViewAPContentStatus.setTextColor(Color.parseColor("#ff96be"));
                mBinding.btnEditDetailPin.setText("SAVE");
                mBinding.btnShareDetailPin.setText("REMOVE FROM LIST");
                mBinding.textViewAPContentStatus.setBackgroundResource(R.drawable.custom_content_pin_pink);
            }else if(item.getStatus().equals("TO BE ACTIVATED")||item.getStatus().equals("EXPIRES IN 8 HOURS")){
                mBinding.textViewAPContentStatus.setTextColor(Color.parseColor("#ffb43c"));
                mBinding.textViewAPContentStatus.setBackgroundResource(R.drawable.custom_content_status_pin);
            }
            if(item.getType() == 0)
            {
                mBinding.headerDetailPin.setText("ASSIGNED PIN");
            }
            else if(item.getType() == 1)
            {
                mBinding.headerDetailPin.setText("ONE TIME PIN");
            }else{
                mBinding.headerDetailPin.setText("PERMANENT PIN");
            }

            position = bundle.getInt("Position");
        }

        // Click button with text: EDIT OR SAVE
        mBinding.btnEditDetailPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBinding.btnEditDetailPin.getText().equals("EDIT"))
                {
                    mBinding.btnEditDetailPin.setText("SAVE");
                    mBinding.btnShareDetailPin.setText("REMOVE FROM LIST");
                    if(item.getType() ==0) {
                        mBinding.headerDetailPin.setText("EDIT ASSIGNED PIN");
                    }
                    mBinding.textViewAPContentName.setVisibility(View.GONE);
                    mBinding.textViewName.setVisibility(View.GONE);
                    mBinding.editTextNamePin.setVisibility(View.VISIBLE);
                }
                else if(mBinding.btnEditDetailPin.getText().equals("SAVE")) {
                    if (!"".equals(mBinding.editTextNamePin.getText().toString())) {
                        arrayList.get(position).setName(mBinding.editTextNamePin.getText().toString());
                        Prefs.applyObjectPrefs(getContext(), Constant.ARRAY_PIN, arrayList);
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
                    }
                    else{
                        AlertDialog.Builder alert  = new AlertDialog.Builder(getActivity());
                        alert.setMessage("You have to fill full information");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Send data which want to be deleted from the list to Assigned Lock Pin

                            }
                        });
                        alert.show();
                    }
                }

            }
        });
        // Click button with text: SHARE OR REMOVE FROM LIST
        mBinding.btnShareDetailPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mBinding.btnShareDetailPin.getText().equals("SHARE"))
                {
                    Intent myIntentShare = new Intent(Intent.ACTION_SEND);
                    myIntentShare.setType("text/plain");
                    String shareBody ="";
                    String shareSub ="";
                    myIntentShare.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                    myIntentShare.putExtra(Intent.EXTRA_TEXT,shareBody);
                    startActivity(Intent.createChooser(myIntentShare,"Share"));

                }
              //  else if(mBinding.btnShareDetailPin.getText().equals("REMOVE FROM LIST"))
                else
                {
                    // Confirm deleted
                    AlertDialog.Builder alert  = new AlertDialog.Builder(getActivity());
                    alert.setMessage("Are you sure you want to remove assigned from list?");
                    alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            arrayList.remove(position);
                            Prefs.applyObjectPrefs(getContext(), Constant.ARRAY_PIN, arrayList);
                            mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN,null, Enumes.Direction.RIGHT_IN);
                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();

               }
            }
        });
        // Click "Back button" to return Assigned Lock Pin
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
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
