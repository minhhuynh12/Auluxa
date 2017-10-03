package net.dirox.auluxa.security.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SecurityDoorLockAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentSecurityBinding;
import net.dirox.auluxa.databinding.LayoutItemSecurityDoorLockBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

/**
 * Created by an on 6/20/2017.
 */

public class FragmentSecurityDoorLock extends BaseFragment {

    private FragmentSecurityBinding mBinding;
    private FragmentInteraction mInteraction;
    SecurityDoorLockAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security, container, false);
        mBinding.setName("DOOR LOCK");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mBinding.recyclerView.setLayoutManager(mLayoutManager);

        adapter = new SecurityDoorLockAdapter(getActivity()) {
            @Override
            public void onItemSelected(int position) {

                switch (position) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putInt("Pin", 0);
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                    case 1:
                        bundle = new Bundle();
                        bundle.putInt("Pin", 0);
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK, bundle, Enumes.Direction.RIGHT_IN);
                        break;
                }
            }

            @Override
            public void showPopUp(int position) {
                Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_security_video_password);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText etPassword = (EditText) dialog.findViewById(R.id.etPassword);

                dialog.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etPassword.getText().length() > 0) {
                            adapter.setSelected(position);
                            dialog.dismiss();
                        }
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
        };
        mBinding.recyclerView.setAdapter(adapter);

        mBinding.recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), R.drawable.line_divider));
        mBinding.securityTitleButtoneprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_CONNECT_TO_IGLOOHOME, null, Enumes.Direction.RIGHT_IN);
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
