package net.dirox.auluxa.fragments.room_centre;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentRoomCentreOthersBinding;

/**
 * Created by an on 6/12/2017.
 */

public class RoomCentreOthersFragment extends BaseFragment {

    FragmentRoomCentreOthersBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre_others, container, false);
        return mBinding.getRoot();
    }
}
