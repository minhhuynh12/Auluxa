package net.dirox.auluxa.security.view;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import net.dirox.auluxa.R;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentSecurityVideoPhoneBinding;
import net.dirox.auluxa.widget.SlideToUnlockView;

/**
 * Created by an on 6/20/2017.
 */

public class FragmentSecurityVideoPhone extends BaseFragment {

    private FragmentSecurityVideoPhoneBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_video_phone, container, false);
        mBinding.setName("VIDEO PHONE");
        mBinding.securityTitleButtoneprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mBinding.slideToUnloclView.setOnUnlockListener(new SlideToUnlockView.OnUnlockListener() {
            @Override
            public void onUnlock() {
                showDialogPassword();
                mBinding.slideToUnloclView.reset();
            }
        });

        return mBinding.getRoot();
    }

    private void showDialogPassword() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_security_video_password);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}
