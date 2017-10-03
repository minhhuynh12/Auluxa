package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.LayoutRoomSecurityDetailBinding;

/**
 * Created by an on 6/29/2017.
 */

public class CreateScenesRoomSecurityDetailFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private LayoutRoomSecurityDetailBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_room_security_detail, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.imgArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    switch (mBinding.tvLock3.getText().toString()) {
                        case "UNLOCK":
                            mBinding.tvLock3.setText("LOCK");
                            break;
                        case "LOCK":
                            mBinding.tvLock3.setText("UNLOCK");
                            break;
                    }
                } catch (Exception e) {
                }

            }
        });

        mBinding.imgArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    switch (mBinding.tvLock3.getText().toString()) {
                        case "UNLOCK":
                            mBinding.tvLock3.setText("LOCK");
                            break;
                        case "LOCK":
                            mBinding.tvLock3.setText("UNLOCK");
                            break;
                    }
                } catch (Exception e) {
                }
            }
        });

        mBinding.imgArrowLeft2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    switch (mBinding.tvLock4.getText().toString()) {
                        case "UNLOCK":
                            mBinding.tvLock4.setText("LOCK");
                            break;
                        case "LOCK":
                            mBinding.tvLock4.setText("UNLOCK");
                            break;
                    }
                } catch (Exception e) {
                }

            }
        });

        mBinding.imgArrowRight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    switch (mBinding.tvLock4.getText().toString()) {
                        case "UNLOCK":
                            mBinding.tvLock4.setText("LOCK");
                            break;
                        case "LOCK":
                            mBinding.tvLock4.setText("UNLOCK");
                            break;
                    }
                } catch (Exception e) {
                }
            }
        });

        mBinding.btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (Integer.parseInt(mBinding.btnSaveEdit.getTag().toString())) {
                    case 1:
                        mBinding.btnSaveEdit.setText("SAVE");
                        mBinding.imgArrowLeft.setVisibility(View.VISIBLE);
                        mBinding.imgArrowRight.setVisibility(View.VISIBLE);
                        mBinding.tvLock3.setTextColor(Color.parseColor("#18bd9b"));

                        mBinding.imgArrowLeft2.setVisibility(View.VISIBLE);
                        mBinding.imgArrowRight2.setVisibility(View.VISIBLE);
                        mBinding.tvLock4.setTextColor(Color.parseColor("#18bd9b"));

                        mBinding.btnSaveEdit.setTag(2);
                        break;
                    case 2:
                        mBinding.btnSaveEdit.setText("EDIT");
                        mBinding.imgArrowLeft.setVisibility(View.GONE);
                        mBinding.imgArrowRight.setVisibility(View.GONE);
                        mBinding.tvLock3.setTextColor(Color.parseColor("#B0000000"));
                        mBinding.tvLock3.setPadding(0, 0, 16, 0);

                        mBinding.imgArrowLeft2.setVisibility(View.GONE);
                        mBinding.imgArrowRight2.setVisibility(View.GONE);
                        mBinding.tvLock4.setTextColor(Color.parseColor("#B0000000"));
                        mBinding.tvLock4.setPadding(0, 0, 16, 0);

                        mBinding.btnSaveEdit.setTag(1);

                        getActivity().onBackPressed();
                        break;
                }


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
