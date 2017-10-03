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
import net.dirox.auluxa.databinding.FragmentSecurityConnecttoigloohomeBinding;
import net.dirox.auluxa.extras.Enumes;

/**
 * Created by MyPC on 27/07/2017.
 */

public class FragmentConnectToIGLooHome extends BaseFragment {
    private FragmentSecurityConnecttoigloohomeBinding mBinding;
    private FragmentInteraction mInteraction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_security_connecttoigloohome , container , false);
        mBinding.btnDoorlockConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_DOOR_LOCK , null , Enumes.Direction.RIGHT_IN);
            }
        });
        mBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY, null , Enumes.Direction.RIGHT_IN);
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
