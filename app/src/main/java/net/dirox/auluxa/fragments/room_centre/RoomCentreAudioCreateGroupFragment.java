package net.dirox.auluxa.fragments.room_centre;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import net.dirox.auluxa.R;
import net.dirox.auluxa.activity.FragmentInteraction;
import net.dirox.auluxa.adapter.audio.CreateGroupAudioDeviceAdapter;
import net.dirox.auluxa.common.fragment.BaseFragment;
import net.dirox.auluxa.data.sample.AudioDeviceItem;
import net.dirox.auluxa.data.sample.AudioGroupDeviceItem;
import net.dirox.auluxa.data.sample.AudioGroupDeviceItemData;
import net.dirox.auluxa.databinding.FragmentRoomCentreAudioCreateGroupBinding;
import net.dirox.auluxa.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by an on 6/28/2017.
 */

public class RoomCentreAudioCreateGroupFragment extends BaseFragment {

    private FragmentInteraction mInteraction;
    FragmentRoomCentreAudioCreateGroupBinding mBinding;

    private AudioGroupDeviceItem groupDevice;
    ArrayList<AudioDeviceItem> arrayAudioDevice;
    ArrayList<AudioDeviceItem> arrayAudioDeviceTransit;
    static Bundle args = new Bundle();
    String strAudioGroupDeviceItem = "";
    int pos = -1;


    public static RoomCentreAudioCreateGroupFragment newInstance(Bundle bundle) {
        RoomCentreAudioCreateGroupFragment fragmentFirst = new RoomCentreAudioCreateGroupFragment();
        if (bundle != null)
            args = bundle;
        else args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_centre_audio_create_group, container, false);
        mBinding.editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        if (strAudioGroupDeviceItem != null && !strAudioGroupDeviceItem.trim().isEmpty())
            strAudioGroupDeviceItem = "";
        else
            strAudioGroupDeviceItem = args.getString("Object");
        pos = args.getInt("position");
        AudioGroupDeviceItemData audioGroupDeviceItem = new Gson().fromJson(strAudioGroupDeviceItem, AudioGroupDeviceItemData.class);
        if (audioGroupDeviceItem == null || audioGroupDeviceItem.items.size() == 0) {
            arrayAudioDeviceTransit = new ArrayList<>();
        } else {
            arrayAudioDeviceTransit = audioGroupDeviceItem.items;
            mBinding.editText.setText(audioGroupDeviceItem.title);
        }

        arrayAudioDevice = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new SimpleDividerItemDecoration(getContext(), R.drawable.audio_device_line_divider);
        mBinding.recyclerView.addItemDecoration(dividerItemDecoration);

        CreateGroupAudioDeviceAdapter adapter = new CreateGroupAudioDeviceAdapter(getActivity(), arrayAudioDeviceTransit) {
            @Override
            public void onItemChangeSelectState(ArrayList<AudioDeviceItem> arraySelected) {
                arrayAudioDevice = arraySelected;
                String groupName = mBinding.editText.getText().toString();
                if (arraySelected.size() >= 2 && groupName.length() > 0) {
                    mBinding.btnSave.setEnabled(true);
                } else {
                    mBinding.btnSave.setEnabled(false);
                }
            }
        };

        mBinding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String groupName = mBinding.editText.getText().toString();
                if (arrayAudioDevice.size() >= 2 && groupName.length() > 0) {
                    mBinding.btnSave.setEnabled(true);
                } else {
                    mBinding.btnSave.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.recyclerView.setAdapter(adapter);

        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = mBinding.editText.getText().toString().toUpperCase();
                groupDevice = new AudioGroupDeviceItem(groupName, arrayAudioDevice);
                if (audioGroupDeviceItem == null || audioGroupDeviceItem.items.size() == 0) {
                    RoomCentreAudioDevicesFragment.addNewGroupAudioDevice(groupDevice);
                    getActivity().onBackPressed();
                } else {
                    RoomCentreAudioDevicesFragment.updateGroupAudioDevice(pos, groupDevice);
                    getActivity().onBackPressed();
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
