package net.dirox.auluxa.security.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentSecurityCreateLockPinBinding;
import net.dirox.auluxa.extras.Enumes;

/**
 * Created by DoanThiPhuongHuyen on 29/07/2017.
 */

public class FragmentSecurityCreateLockPin extends BaseFragment {
    private FragmentSecurityCreateLockPinBinding mBinding;
    private FragmentInteraction mInteraction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_create_lock_pin, container, false);
        mBinding.btnOneTimePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle pbundle = new Bundle();
                pbundle.putInt("Type",1);
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_CREATE_ONETIME_OR_PERMANENT_PIN, pbundle, Enumes.Direction.RIGHT_IN);

            }
        });
        mBinding.btnPermanentPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle pbundle = new Bundle();
                pbundle.putInt("Type",2);
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_CREATE_ONETIME_OR_PERMANENT_PIN, pbundle, Enumes.Direction.RIGHT_IN);

            }
        });
        mBinding.btnDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle pbundle = new Bundle();
                pbundle.putInt("Type",3);
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_CREATE_DURATION_PIN, pbundle, Enumes.Direction.RIGHT_IN);

            }
        });
        mBinding.btnBackAssignedLockPin.setOnClickListener(new View.OnClickListener() {
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
