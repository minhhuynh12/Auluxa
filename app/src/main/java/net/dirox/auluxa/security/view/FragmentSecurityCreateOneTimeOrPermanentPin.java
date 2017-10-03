package net.dirox.auluxa.security.view;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
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
import net.dirox.auluxa.databinding.FragmentSecurityCreateOnetimeOrPermanentPinBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;

import static net.dirox.auluxa.security.view.FragmentSecurityAssignedLockPin.arrayList;

/**
 * Created by DoanThiPhuongHuyen on 29/07/2017.
 */

public class FragmentSecurityCreateOneTimeOrPermanentPin extends BaseFragment {
    private FragmentSecurityCreateOnetimeOrPermanentPinBinding mBinding;
    private FragmentInteraction mInteraction;
    int type =0;
    public static AssignedLockPinItem assignedLockPinItem;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_create_onetime_or_permanent_pin, container, false);
        Bundle rbundle = getArguments();
        if (rbundle != null)
        {
             type = rbundle.getInt("Type");
            if(type == 1) {
                mBinding.titleHeader.setText("ONE TIME PIN");
                mBinding.editTextCreateOneTimeOrPermanentPin.setText("");

            }
            else if(type == 2){
                mBinding.titleHeader.setText("PERMANENT PIN");
                mBinding.editTextCreateOneTimeOrPermanentPin.setText("");
            }
            mBinding.btnCreateOneTimeOrPermanentPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String name = mBinding.editTextCreateOneTimeOrPermanentPin.getText().toString();
                    if(mBinding.editTextCreateOneTimeOrPermanentPin.getText().toString().equals(""))
                    {
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
                    else if(checkData(arrayList,mBinding.editTextCreateOneTimeOrPermanentPin.getText().toString())){
                        AlertDialog.Builder alert  = new AlertDialog.Builder(getActivity());
                        alert.setMessage("Information already exists, please enter information again");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alert.show();
                    }
                    else {
                        if (type == 1) {
                            assignedLockPinItem = new AssignedLockPinItem(mBinding.editTextCreateOneTimeOrPermanentPin.getText().toString(),
                                    "05200924", R.drawable.arrow, "ONE TIME PIN", "EXPIRES IN 8 HOURS", 1);
                        } else if (type == 2) {
                            assignedLockPinItem = new AssignedLockPinItem(mBinding.editTextCreateOneTimeOrPermanentPin.getText().toString(),
                                    "19871987", R.drawable.arrow, "PERMANENT", "IN USE", 2);
                        }
                        arrayList.add(arrayList.size() - 1, assignedLockPinItem);
                        Prefs.applyObjectPrefs(getContext(), Constant.ARRAY_PIN, arrayList);
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_ASSIGNED_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
                    }
                }
            });

        }
        mBinding.btnBackCreateLockPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_CREATE_LOCK_PIN, null, Enumes.Direction.RIGHT_IN);
            }
        });
        return mBinding.getRoot();
    }
    public boolean checkData(ArrayList<AssignedLockPinItem> arrayList, String name)
    {
        for(int i = 0;i< arrayList.size()-1;i++)
            if(arrayList.get(i).getName().equals(name))
            {
                return true;
            }
        return false;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }
}
