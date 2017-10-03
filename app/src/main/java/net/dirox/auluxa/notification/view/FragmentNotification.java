package net.dirox.auluxa.notification.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentNotificationsBinding;
import net.dirox.auluxa.databinding.LayoutSetuploginBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.notification.FragmentNotificationContract;
import net.dirox.auluxa.security.view.FragmentSecurity;

/**
 * Created by minh on 12/06/2017.
 */

public class FragmentNotification extends BaseFragment implements FragmentNotificationContract.View {
    private FragmentNotificationsBinding mBinding;

    private FragmentInteraction mInteraction;

        public static FragmentNotification newInstance(){
            Bundle args = new Bundle();

            FragmentNotification fragment = new FragmentNotification();
            fragment.setArguments(args);
            return fragment;
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);

        mBinding.btnBackNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnSaveNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnCancelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
            }
        });

        return mBinding.getRoot();

    }

    @Override
    public void showNotification() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

}
