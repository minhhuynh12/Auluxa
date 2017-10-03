package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ScenesItem;
import net.dirox.auluxa.databinding.FragmentScenesCreateStep1Binding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;

/**
 * Created by an on 6/28/2017.
 */

public class CreateScenesStep1Fragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private FragmentScenesCreateStep1Binding mBinding;

    public static ScenesItem scenesItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_step_1, container, false);
        /*scenesItem = new ScenesItem();
        mBinding.setItem(scenesItem);*/

        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.editText.length() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.CREATE_SCENES_NAME, mBinding.editText.getText().toString());
                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_STEP_2, bundle, Enumes.Direction.RIGHT_IN);
                } else {
                    ((BaseActivity) getActivity()).showAlert("", "Scene name must be not empty");
                }
            }
        });

        mBinding.backgroundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).hideKeyboard();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInteraction = (FragmentInteraction) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        scenesItem = null;
    }

}
