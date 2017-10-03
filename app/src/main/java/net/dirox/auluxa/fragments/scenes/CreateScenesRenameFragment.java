package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.databinding.FragmentScenesCreateRenameBinding;

/**
 * Created by an on 6/29/2017.
 */

public class CreateScenesRenameFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private FragmentScenesCreateRenameBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_rename, container, false);
        mBinding.setItem(CreateScenesStep1Fragment.scenesItem);
        mBinding.editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mBinding.editText.getText().toString().toUpperCase();
                CreateScenesStep1Fragment.scenesItem.titleScenes = name;
                getActivity().onBackPressed();
            }
        });

        mBinding.textView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).hideKeyboard();
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
