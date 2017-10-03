package net.dirox.auluxa.setting.view.device_list.video;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.SettingsLanguageAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.SettingsLanguageItem;
import net.dirox.auluxa.databinding.LayoutSettingsVideoSelectCategoryBinding;
import net.dirox.auluxa.extras.Enumes;
import net.dirox.auluxa.utils.Constant;
import net.dirox.auluxa.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

public class FragmentVideoSelectCategory extends BaseFragment {

    private LayoutSettingsVideoSelectCategoryBinding mBinding;

    private FragmentInteraction mInteraction;

    List<SettingsLanguageItem> list;

    SettingsLanguageAdapter adapter;

    int pos;

    public static FragmentVideoSelectCategory newInstance() {

        Bundle args = new Bundle();

        FragmentVideoSelectCategory fragment = new FragmentVideoSelectCategory();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_settings_video_select_category, container, false);

        mBinding.btnBackSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackAndHideKeyBoard();
            }
        });

        list = new ArrayList<SettingsLanguageItem>();
        list.add(new SettingsLanguageItem(0, "TV", false));
        list.add(new SettingsLanguageItem(1, "VCR", false));
        list.add(new SettingsLanguageItem(2, "DVD", false));
        list.add(new SettingsLanguageItem(3, "AUD", false));
        list.add(new SettingsLanguageItem(4, "CD", false));
        list.add(new SettingsLanguageItem(5, "OTHERS", false));

        adapter = new SettingsLanguageAdapter(getActivity(), R.layout.layout_item_settings_language, list) {
            @Override
            public void onItemSelected(int position) {
                pos = position;
                Log.d("VIDEO SELECT DEVICE: ", list.get(pos).getLanguage());
            }
        };
        mBinding.listVideoSelectCategory.setAdapter(adapter);

        mBinding.btnSaveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.setStringPrefs(getContext(), Constant.VIDEO_SELECT_CATEGORY, list.get(pos).getLanguage());
                getActivity().onBackPressed();
            }
        });

        mBinding.btnCancelCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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
