package net.dirox.auluxa.security.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentSecurityEditnameBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Prefs;

/**
 * Created by MyPC on 28/07/2017.
 */

public class FragmentSecurityLockEditName extends BaseFragment {
    private FragmentSecurityEditnameBinding mBinding;
    private FragmentInteraction mInteraction;
    String text= "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_editname, container, false);
        Bundle rep = getArguments();
        if(rep != null){
            text =  rep.getString("txt");
            mBinding.editEditName.setText(text);
        }

        mBinding.btnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.setStringPrefs(getContext(), "name", mBinding.editEditName.getText().toString());
                onBackAndHideKeyBoard();
            }
        });
        mBinding.btnBackEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SECURITY_LOCK, null, Enumes.Direction.RIGHT_IN);
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
