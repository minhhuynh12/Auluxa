package net.dirox.auluxa.fragments.scenes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.common.activity.BaseActivity;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.ScenesItem;
import net.dirox.auluxa.databinding.FragmentScenesCreateStep2Binding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by an on 6/28/2017.
 */

public class CreateScenesStep2Fragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    private FragmentScenesCreateStep2Binding mBinding;
    public static ScenesItem scenesItem;
    String scenes_tmp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenes_create_step_2, container, false);

        //mBinding.setVariable(BR.item, CreateScenesStep1Fragment.scenesItem);
        scenesItem = new ScenesItem();
        Bundle bundle = this.getArguments();

        if (bundle.get(Constant.SCENES_NAME) != null) {
            mBinding.setItem(scenesItem);
            scenesItem.titleScenes = bundle.getString(Constant.SCENES_NAME);
            mBinding.etSceneName.setText(scenesItem.titleScenes);
            scenes_tmp = scenesItem.titleScenes;
            mBinding.tvHeaderTitle.setText("EDIT SCENE");
        } else if (bundle.get(Constant.CREATE_SCENES_NAME) != null) {
            scenesItem.titleScenes = mBinding.etSceneName.getText().toString();
            mBinding.tvHeaderTitle.setText("CREATE SCENE");
            mBinding.btnDelete.setText("CANCEL");
        }


        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackAndHideKeyBoard();
            }
        });

        // Rooms
        mBinding.btnRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_ROOMS, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Schedule
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_SCHEDULE, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnSequence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sequence
                mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_SCENE_CREATE_SEQUENCE, null, Enumes.Direction.RIGHT_IN);
            }
        });

        mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.btnDelete.getText().equals("DELETE")) {
                    AlertDialog mLogOutAlert = new AlertDialog.Builder(getActivity())
                            .setTitle("Alert!")
                            .setMessage("Are you sure to delete?")
                            .setNegativeButton("OK", (dialog, i) -> {
                                String[] oldStrings = Prefs.loadArrayPrefs(getContext(), Constant.ARRAY_SCENES_NAME);
                                List<String> stringList = new ArrayList<String>(Arrays.asList(oldStrings));

                                if (stringList.contains(scenesItem.titleScenes)) {
                                    stringList.remove(scenesItem.titleScenes);
                                    Prefs.saveArrayPrefs(getContext(), Constant.ARRAY_SCENES_NAME, stringList.toArray(new String[stringList.size()]));
                                    mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
                                } else {

                                }

                            })
                            .setPositiveButton("Cancel", (dialog, which) -> dialog.dismiss())
                            .create();
                    mLogOutAlert.show();
                } else {
                    onBackAndHideKeyBoard();
                }
            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBinding.etSceneName.getText().toString().trim().equals("")) {
                    ((BaseActivity) getActivity()).showAlert("Alert!", "Scene is not empty!");
                    return;
                }

                String[] oldStrings = Prefs.loadArrayPrefs(getContext(), Constant.ARRAY_SCENES_NAME);
                List<String> stringList = new ArrayList<String>(Arrays.asList(oldStrings));

                if (mBinding.tvHeaderTitle.getText().toString().equals("CREATE SCENE")) {
                    if (stringList.contains(mBinding.etSceneName.getText().toString())) {
                        ((BaseActivity) getActivity()).showAlert("Alert!", "Scene is existed!");
                    } else {
                        stringList.add(mBinding.etSceneName.getText().toString());
                        Prefs.saveArrayPrefs(getContext(), Constant.ARRAY_SCENES_NAME, stringList.toArray(new String[stringList.size()]));
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
                    }
                } else {
                    if (mBinding.etSceneName.getText().toString().equals(scenes_tmp)) {
                        onBackAndHideKeyBoard();
                    } else {
                        int index = stringList.indexOf(scenes_tmp);
                        stringList.set(index, mBinding.etSceneName.getText().toString());

                        Prefs.saveArrayPrefs(getContext(), Constant.ARRAY_SCENES_NAME, stringList.toArray(new String[stringList.size()]));
                        mInteraction.openFragment(Enumes.FragmentType.FRAGMENT_MAIN_HOME, null, Enumes.Direction.RIGHT_IN);
                    }
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
